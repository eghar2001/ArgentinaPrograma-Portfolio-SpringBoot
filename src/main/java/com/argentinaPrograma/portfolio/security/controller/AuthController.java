/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.security.controller;

import com.argentinaPrograma.portfolio.dto.Mensaje;
import com.argentinaPrograma.portfolio.security.dto.JwtDto;
import com.argentinaPrograma.portfolio.security.dto.LoginUsuario;
import com.argentinaPrograma.portfolio.security.dto.NuevoUsuario;
import com.argentinaPrograma.portfolio.security.entity.Rol;
import com.argentinaPrograma.portfolio.security.entity.Usuario;
import com.argentinaPrograma.portfolio.security.entity.ValidacionAuth;
import com.argentinaPrograma.portfolio.security.enums.RolNombre;
import com.argentinaPrograma.portfolio.security.jwt.JwtProvider;
import com.argentinaPrograma.portfolio.security.service.RolService;
import com.argentinaPrograma.portfolio.security.service.UsuarioService;
import com.argentinaPrograma.portfolio.security.service.ValidacionAuthServ;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nahux
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"https://miportfolioanguar.web.app","http://localhost:4200"})
public class AuthController {
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired 
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UsuarioService usuarioServ;
    
    @Autowired
    private RolService rolServ;
    
    @Autowired
    private JwtProvider jwtProvider;
    
    @Autowired
    private ValidacionAuthServ validsServ;
    
    @PostMapping("/nuevo")
    public ResponseEntity<Mensaje> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        ValidacionAuth valid = this.validsServ.getValidacion();
        Matcher userMatcher = valid.getRegexUser().matcher(nuevoUsuario.getNombreUsuario());
        Matcher emailMatcher = valid.getRegexEmail().matcher(nuevoUsuario.getEmail());
        Matcher passMatcher = valid.getRegexPass().matcher(nuevoUsuario.getPassword());
        if(bindingResult.hasErrors()){
            return new ResponseEntity(new Mensaje( "Campos mal puestos o email invalido"),HttpStatus.BAD_REQUEST);
        }
        /*
        Validaciones que tambien estan en el form del front
        */
        //MAXIMOS
        if(nuevoUsuario.getNombreUsuario().length()>valid.getMaxLengthUser()){
            return new ResponseEntity(new Mensaje("Username se excede en longitud"),HttpStatus.BAD_REQUEST);
        }
        if(nuevoUsuario.getEmail().length()>valid.getMaxLengthEmail()){
            return new ResponseEntity(new Mensaje("Email se excede en longitud"),HttpStatus.BAD_REQUEST);
        }
        if( nuevoUsuario.getPassword().length() > valid.getMaxLengthPass()){
            return new ResponseEntity(new Mensaje("Password se excede en longitud"),HttpStatus.BAD_REQUEST);
        }
        //Patrones
        if(!userMatcher.find()){
            return new ResponseEntity(new Mensaje("User no cumple con estandares requeridos"),HttpStatus.BAD_REQUEST);
        }
        if(!emailMatcher.find()){
            return new ResponseEntity(new Mensaje("Email no cumple con estandares requeridos"),HttpStatus.BAD_REQUEST);
        }
        if(!passMatcher.find()){
            return new ResponseEntity(new Mensaje("Password no cumple con estandares requeridos"),HttpStatus.BAD_REQUEST);
        }
        if(usuarioServ.existsByNombreUsuario(nuevoUsuario.getNombreUsuario())){
            return new ResponseEntity(new Mensaje("Ese username ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(usuarioServ.existsByEmail(nuevoUsuario.getEmail())){
            return new ResponseEntity(new Mensaje("Ese email ya existe"),HttpStatus.BAD_REQUEST);
        }
        Usuario usuario = new Usuario(nuevoUsuario.getNombreUsuario(),nuevoUsuario.getEmail(),
            passwordEncoder.encode(nuevoUsuario.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolServ.getByRolNombre(RolNombre.ROLE_USER).get());
        /*
        Por razones de seguridad, el rol de Admin lo doy desde la BBDD
        
        
        if(nuevoUsuario.getRoles().contains("admin")){
            roles.add(rolServ.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        }*/
        usuario.setRoles(roles);
        usuarioServ.save(usuario);
        return new ResponseEntity(new Mensaje("Usuario guardado"),HttpStatus.OK);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUsuario loginUsuario,BindingResult bindingResult){
         if(bindingResult.hasErrors()){
            return new ResponseEntity(new Mensaje("Campos mal puestos"),HttpStatus.BAD_REQUEST);
        }
         /*
         Podria poner las validaciones aca, pero a esta altura da lo mismo
         */
        Authentication authentication  = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(),loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.GenerateToken(authentication);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt,userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto,HttpStatus.OK);
    }
    
    /*
    Funciones para el manejo del admin
    */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/altaAdmin/{username}")
    public ResponseEntity<Mensaje> altaAdmin(@PathVariable String username){
        Usuario user = this.usuarioServ.getByNombreUsuario(username).get();
        if(user == null){
            return new ResponseEntity(new Mensaje("Usuario no encontrado"),HttpStatus.BAD_REQUEST);
        }
        user.getRoles().add(this.rolServ.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        
        this.usuarioServ.save(user);
        return new ResponseEntity(new Mensaje(username +" es admin"),HttpStatus.OK);    
    }
    
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/usuario/traer")
    public List<Usuario> getUsuarios(){
        return this.usuarioServ.getUsuarios();
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/usuario/borrar/{username}")
    public void deleteUsuario(@PathVariable String username){
        this.usuarioServ.deleteUsuarioByUsername(username);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/usuario/edit/{usernameActual}")
    public ResponseEntity<Mensaje> edit(@PathVariable String usernameActual , @RequestBody Usuario edittedUser){
        Usuario user = this.usuarioServ.getByNombreUsuario(usernameActual).get();
        if(user == null){
            return new ResponseEntity(new Mensaje(usernameActual+" no fue encontrado!"), HttpStatus.BAD_REQUEST);
        }
        edittedUser.setId(user.getId());
        edittedUser.setRoles(user.getRoles());
        edittedUser.setPassword(this.passwordEncoder.encode(edittedUser.getPassword()));
        if (usernameActual.equals(edittedUser.getNombreUsuario()) ){
            if(user.getEmail().equals(edittedUser.getEmail())){                
                this.usuarioServ.save(edittedUser);
                return new ResponseEntity(new Mensaje(usernameActual+" fue editado!"), HttpStatus.OK);
            }
            else if(this.usuarioServ.existsByEmail(edittedUser.getEmail())){
                return new ResponseEntity(new Mensaje(edittedUser.getEmail()+" ya existe!"), HttpStatus.BAD_REQUEST);
            } 
            else{
                this.usuarioServ.save(edittedUser);
                return new ResponseEntity(new Mensaje(usernameActual+" fue editado!"), HttpStatus.OK);
            }
        }else if(this.usuarioServ.existsByNombreUsuario(edittedUser.getNombreUsuario())){
            return new ResponseEntity(new Mensaje(edittedUser.getNombreUsuario()+" ya existe!"), HttpStatus.BAD_REQUEST);
        }
        else{
            this.usuarioServ.save(edittedUser);
            return new ResponseEntity(new Mensaje(usernameActual+" fue editado!"), HttpStatus.OK);
        }
        
        
    }
    /*
    Edicion de validaciones
    */
    @GetMapping("/validaciones")
    @ResponseBody
    public ValidacionAuth getValidaciones(){
        return this.validsServ.getValidacion();
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/validaciones/edit")
    public ValidacionAuth editValidaciones(@RequestBody ValidacionAuth valids){
        valids.setId(1);
        return this.validsServ.saveValidacion(valids);
    }
    
    
    
    
    
}

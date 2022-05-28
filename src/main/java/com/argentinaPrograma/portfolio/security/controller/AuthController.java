/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.security.controller;

import com.argentinaPrograma.portfolio.dto.Mensaje;
import com.argentinaPrograma.portfolio.security.dto.JwtDto;
import com.argentinaPrograma.portfolio.security.dto.LoginUsuario;
import com.argentinaPrograma.portfolio.security.dto.NuevoUsuario;
import com.argentinaPrograma.portfolio.security.dto.ValidacionesAuth;
import com.argentinaPrograma.portfolio.security.entity.Rol;
import com.argentinaPrograma.portfolio.security.entity.Usuario;
import com.argentinaPrograma.portfolio.security.enums.RolNombre;
import com.argentinaPrograma.portfolio.security.jwt.JwtProvider;
import com.argentinaPrograma.portfolio.security.service.RolService;
import com.argentinaPrograma.portfolio.security.service.UsuarioService;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@CrossOrigin
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
    
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        ValidacionesAuth valid = new ValidacionesAuth();
        Matcher userMatcher = valid.getRegexUser().matcher(nuevoUsuario.getNombreUsuario());
        Matcher emailMatcher = valid.getRegexEmail().matcher(nuevoUsuario.getEmail());
        Matcher passMatcher = valid.getRegexPass().matcher(nuevoUsuario.getPassword());
        if(bindingResult.hasErrors()){
            return new ResponseEntity(new Mensaje("Campos mal puestos o email invalido"),HttpStatus.BAD_REQUEST);
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
        return new ResponseEntity(new Mensaje("Usuario guardado"),HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario,BindingResult bindingResult){
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
    
    @GetMapping("/validaciones")
    @ResponseBody
    public ValidacionesAuth getValidaciones(){
        return new ValidacionesAuth();
    }
    
}

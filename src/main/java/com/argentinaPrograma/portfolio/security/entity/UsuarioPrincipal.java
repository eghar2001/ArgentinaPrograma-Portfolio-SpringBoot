/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.security.entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author nahux
 */
public class UsuarioPrincipal implements UserDetails {
    /*
    Clase encargada de generar la seguridad
    Es lo mismo que la entidad Rol pero con autoridades en vez
    de roles
    */


    
    private String nombreUsuario;

    private String email;
   
    private String password;
    
    private Collection<? extends GrantedAuthority> authorities;

    public UsuarioPrincipal(String nombreUsuario, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }
    
    /*
    Metodo que pasa un usuario entidad de nuestra BBDD 
        a un usuario con sus autoridades, obviamente mapeando los roles de la BBDD a autoridades
    */
    public static UsuarioPrincipal build(Usuario usuario){
        List<GrantedAuthority> authorities = usuario.getRoles().stream().
                map(rol -> new SimpleGrantedAuthority(rol.getRolNombre().name())).
                collect(Collectors.toList());
        return new UsuarioPrincipal(usuario.getNombreUsuario(), usuario.getEmail(), usuario.getPassword(),authorities);
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getPassword() {
       return password; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getUsername() {
       return nombreUsuario; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isAccountNonLocked() {
       return true; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isEnabled() {
       return true; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   

    public String getEmail() {
        return email;
    }
    
    
    
    
}

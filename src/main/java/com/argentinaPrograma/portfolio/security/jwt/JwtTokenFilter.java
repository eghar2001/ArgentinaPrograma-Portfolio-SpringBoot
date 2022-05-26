/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.security.jwt;


import com.argentinaPrograma.portfolio.security.service.UserDetailsServiceImpl;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author nahux
 */
/*
Por cada peticion va a comprobar que el token sea valido
*/
public class JwtTokenFilter extends OncePerRequestFilter {
    
    
    
    @Autowired
    private JwtProvider jwtProv;
    
    @Autowired
    private UserDetailsServiceImpl userDetailsServ;
    
    
    private final static Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);
    
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain fc) throws ServletException, IOException {
       try{
           
           String token = getToken(req);
          if(token != null && jwtProv.validateToken(token)){
               String nombreUsuario = jwtProv.getNombreUsuarioFromToken(token);
               UserDetails userDetails = userDetailsServ.loadUserByUsername(nombreUsuario);
               UsernamePasswordAuthenticationToken auth = 
                       new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
               SecurityContextHolder.getContext().setAuthentication(auth);
           }
       }catch(UsernameNotFoundException e){
           logger.error("fail en el metodo doFilter "+ e.getLocalizedMessage());
       }
       fc.doFilter(req, res);
       
    }
    /*
    Sacamos el bearer de la string
    */
    private String getToken(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer")){
            return header.replace("Bearer ","");
        }
        return null;
    }
    
}

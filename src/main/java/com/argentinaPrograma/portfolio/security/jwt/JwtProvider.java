/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.security.jwt;


import com.argentinaPrograma.portfolio.security.entity.UsuarioPrincipal;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 *
 * @author nahux
 */
/*
Genera el token, y un metodo de validacion para ver que este bien formado
*/
@Component
public class JwtProvider {
    private final static Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);
    
    @Value("${jwt.secret}")
    private String secret ;
    
    @Value("${jwt.expiration}")
    private int expiration  ;
    
    public String GenerateToken(Authentication authentication){
        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal)authentication.getPrincipal();
        return Jwts.builder().setSubject(usuarioPrincipal.getUsername()).
                setIssuedAt(new Date()).
                setExpiration(new Date(new Date().getTime() + expiration * 1000)).                
                signWith(SignatureAlgorithm.HS512, secret).
                compact();
                
    }
    
    public String getNombreUsuarioFromToken(String token){
        
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
        
    }
    public boolean validateToken(String token){
        try{
           
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch(MalformedJwtException e){
            logger.error("token mal formado");
        }catch(UnsupportedJwtException e){
            logger.error("token no soportado");
        }catch(ExpiredJwtException e){
            logger.error("token expirado");
        }catch(IllegalArgumentException e){
            logger.error("token vacio: "+e.getMessage());
        }catch(SignatureException e){
            logger.error("fail en la firma");
        }
        return false;
    }
    
    @Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
            
}

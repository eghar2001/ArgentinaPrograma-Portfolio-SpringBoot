/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author nahux
 */
@Getter @Setter
public class PerfilDto {
    private Long id;
    
    private String nombre;
    
    private String apellido;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-3")
    private LocalDate fechaNac;
    

    private int edad;
    
    private String profesion;
    
 
    
    private String perfilUrl;

    private String bannerUrl; 
    private String localidad;
    private String provincia;
    
    
     private List<RedPerfilDto> redesSociales;
}

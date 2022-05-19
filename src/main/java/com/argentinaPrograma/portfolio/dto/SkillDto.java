/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.dto;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author nahux
 */
@Getter @Setter
public class SkillDto {
    private Long id;
    
    private String descripcion;
    
    private int porcentaje;
    
    private Long idPerfil;
    
    /*
    Estos atributos solo sirven para la 
    recepcion en el backend
    */
    
    
    private Long idTipoSkill;
    
}

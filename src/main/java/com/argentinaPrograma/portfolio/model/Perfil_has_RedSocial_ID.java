/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author nahux
 */
@Embeddable
@Getter @Setter
public class Perfil_has_RedSocial_ID implements Serializable{
    @Column(name="id_perfil")
    private Long idPerfil;
    
    @Column(name="id_red_social")
    private Long idRedSocial;
    
    
}

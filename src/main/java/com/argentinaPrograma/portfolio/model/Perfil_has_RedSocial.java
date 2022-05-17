/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author nahux
 */
@Entity
@Getter @Setter
@Table(name="perfil_has_redsocial")
public class Perfil_has_RedSocial implements Serializable {
    @EmbeddedId
    private Perfil_has_RedSocial_ID id;
    
    @ManyToOne
    @MapsId("id_perfil")
    @JoinColumn(name = "id_perfil")
    Perfil perfil;
    
    @ManyToOne
    @MapsId("id_red_social")
    @JoinColumn(name = "id_red_social")
    RedSocial redSocial;
    
    @Column(name="red_social_url")
    private String redSocialUrl;
    
    
   
}

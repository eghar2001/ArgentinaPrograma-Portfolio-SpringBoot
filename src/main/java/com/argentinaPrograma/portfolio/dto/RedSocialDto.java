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
public class RedSocialDto {
    private String nombreRedSocial;
    private String url;
    private String claseBoxIcon;
    private String color;

    

    public RedSocialDto() {
    }

    public RedSocialDto(String nombreRedSocial, String url, String claseBoxIcon) {
        this.nombreRedSocial = nombreRedSocial;
        this.url = url;
        this.claseBoxIcon = claseBoxIcon;
    }
    
    
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.dto;

import com.argentinaPrograma.portfolio.model.RedSocial;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author nahux
 */
@Getter @Setter
public class RedPerfilDto {
    private RedSocial redSocial;
    
    private String url;

    @Override
    public String toString() {
        return "RedPerfilDto{" + "redSocial=" + redSocial + ", url=" + url + '}';
    }
    
    
}

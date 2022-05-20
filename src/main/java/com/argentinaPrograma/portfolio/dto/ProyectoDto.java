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
public class ProyectoDto {

    private Long id;

    private Long idPerfil;

    private String nombre;
    
    private String descripcion;

    private String url;

    private String fondoUrl;

    @Override
    public String toString() {
        return "ProyectoDto{" + "id=" + id + ", idPerfil=" + idPerfil + ", nombre=" + nombre + ", descripcion=" + descripcion + ", url=" + url + ", fondoUrl=" + fondoUrl + '}';
    }
    
    
}

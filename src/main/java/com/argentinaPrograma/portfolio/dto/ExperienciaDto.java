/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.dto;


import com.argentinaPrograma.portfolio.model.TipoJornada;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author nahux
 */
@Getter @Setter
public class ExperienciaDto {
    
    private Long id;   
    private String nombreInstitucion;
    
    
    private TipoJornada tipoJornada;    
    private Long idPerfil;
 
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-3")    
    private LocalDate fechaDesde;
    

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-3")
    private LocalDate fechaHasta;
    
    private String periodo;
    

    private String cargo;
    

    private String fondoUrl;

    @Override
    public String toString() {
        return "ExperienciaDto{" + "id=" + id + ", nombreInstitucion=" + nombreInstitucion + ", tipoJornada=" + tipoJornada + ", idPerfil=" + idPerfil + ", fechaDesde=" + fechaDesde + ", fechaHasta=" + fechaHasta + ", periodo=" + periodo + ", cargo=" + cargo + ", fondoUrl=" + fondoUrl + '}';
    }
    
    
}

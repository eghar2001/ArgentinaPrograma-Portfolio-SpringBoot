/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import javax.persistence.GeneratedValue;



import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author nahux
 */
@Getter @Setter
public class EducacionDto {
    
    private Long id;

    private String descripcion;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-3")   
    private LocalDate fechaDesde;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-3")   
    private LocalDate fechaHasta;
    private Long idTipoEdu;
    private String nombreInstitucion;
    private Long idPerfil;
    private String fotoInstitucionUrl;

    @Override
    public String toString() {
        return "EducacionDto{" + "id=" + id + ", descripcion=" + descripcion + ", fechaDesde=" + fechaDesde + ", fechaHasta=" + fechaHasta + ", idTipoEdu=" + idTipoEdu + ", nombreInstitucion=" + nombreInstitucion + ", idPerfil=" + idPerfil + ", fotoInstitucionUrl=" + fotoInstitucionUrl + '}';
    }
    
    
   
    
    
}

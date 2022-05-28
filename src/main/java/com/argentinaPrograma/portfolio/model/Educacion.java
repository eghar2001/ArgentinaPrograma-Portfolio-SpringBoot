/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author nahux
 */
@Entity
@Getter @Setter
@Table(name="educacion")
public class Educacion implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    
    @Column(name="descripcion")
    private String descripcion;
    
   
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-3")
    @Column(name="fecha_desde")
    private LocalDate fechaDesde;
    

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-3")
    @Column(name="fecha_hasta")
    private LocalDate fechaHasta;
    
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name="id_tipo_educacion")
    private TipoEducacion tipoEducacion; 
    
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name="institucion_id")
    private Institucion institucion;
    
    @ManyToOne(cascade={CascadeType.MERGE})
    @PrimaryKeyJoinColumn(name="perfil_id",referencedColumnName="usuario_id")
    private Perfil perfil;

    @Override
    public String toString() {
        return "Educacion{" + "id=" + id + ", descripcion=" + descripcion + ", fechaDesde=" + fechaDesde + ", fechaHasta=" + fechaHasta + '}';
    }
    
    
    
    
    
   
    
    
    
}

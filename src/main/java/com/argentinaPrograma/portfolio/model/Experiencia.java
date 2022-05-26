/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@Table(name="experiencia")
public class Experiencia implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(cascade = {CascadeType.MERGE})
    @PrimaryKeyJoinColumn(name="perfil_id",referencedColumnName="usuario_id")
    private Perfil perfil;
    
    @ManyToOne(cascade ={CascadeType.MERGE})
    @JoinColumn(name="id_institucion")
    private Institucion institucion;
    
    @ManyToOne(cascade ={CascadeType.MERGE})
    @JoinColumn(name="id_tipo_jornada")
    private TipoJornada tipoJornada;    
    
   
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-3")
    @Column(name="fecha_desde")
    private LocalDate fechaDesde;
    
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-3")
    @Column(name="fecha_hasta")
    private LocalDate fechaHasta;
    
    @Column(name="cargo")
    private String cargo;
    
   @Column(name="fondo_url")
    private String fondoUrl;
    
    
    
}
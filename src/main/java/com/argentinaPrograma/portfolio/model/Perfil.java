/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDate;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author nahux
 */
@Entity
@Setter @Getter
@Table(name="perfil")
public class Perfil implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    
    @Column(name="nombre")
    private String nombre;
    
    @Column(name="apellido")
    private String apellido;
    
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-3")
    @Column(name="fecha_nac")
    private LocalDate fechaNac;
    
    @Column(name="profesion")
    private String profesion;
    
    @Column(name="email")
    private String email;
    
    @Column(name="contrasenia")
    private String contrasenia;
    
    

    @Column(name="perfil_url")
    private String perfilUrl;
    

    @Column(name="banner_url")
    private String bannerUrl;
    
    
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name="id_localidad")
    private Localidad localidad;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy="perfil")
    private List<Educacion> educaciones;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy="perfil")
    private List<Experiencia> experiencias;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy="perfil")
    private List<Perfil_has_RedSocial> redesSociales;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy="perfil")
    private List<Skill> skills;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy="perfil")
    private List<Proyecto> proyectos;
    
    @Override
    public String toString(){
        return "nombre: "+this.nombre+" apellido: "+this.apellido + " fecha nacimiento: "+this.fechaNac+" localidad: ";
    }
    
}

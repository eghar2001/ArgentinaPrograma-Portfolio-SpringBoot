/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author nahux
 */

@Entity
@Getter @Setter
@Table(name="provincia")
public class Provincia implements Serializable{
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="nombre")
    @NotNull
    private String nombre;
    
  
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy="provincia")
    @NotNull
    private List<Localidad> localidades;
    
    @Override
    public String toString(){
        return "nombre: "+this.nombre ;
    }
    
    
}

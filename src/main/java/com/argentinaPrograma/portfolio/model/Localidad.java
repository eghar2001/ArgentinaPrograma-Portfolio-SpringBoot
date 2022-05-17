/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.model;

import com.sun.istack.NotNull;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author nahux
 */
@Entity
@Getter @Setter
@Table(name="localidad")
public class Localidad implements Serializable{
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="nombre")
    @NotNull
    private String nombre;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="id_provincia",referencedColumnName="id")
    @NotNull
    private Provincia provincia;
    
    @Override
    public String toString(){
        return "nombre: "+this.nombre; 
    }
    
   
    
   
}

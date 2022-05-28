/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author nahux
 */
@Entity
@Getter @Setter
@Table(name="institucion")
public class Institucion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    
    @Column(name="nombre")
    private String nombre;

   @Column(name="url_logo")
    private String logoUrl;

    @Override
    public String toString() {
        return "Institucion{" + "id=" + id + ", nombre=" + nombre + ", logoUrl=" + logoUrl + '}';
    }
    
}

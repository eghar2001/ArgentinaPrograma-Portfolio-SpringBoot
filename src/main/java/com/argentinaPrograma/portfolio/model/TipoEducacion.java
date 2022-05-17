/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name="tipoeducacion")
public class TipoEducacion implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    
    @Column(name="descripcion")
    private String descripcion;
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy="tipoEducacion",cascade=CascadeType.ALL)
    private List<Educacion> educaciones;
    
    
    
    @Override
    public String toString(){
        return "id: "+this.getId() + " descripcion: "+this.getDescripcion();
    }
}

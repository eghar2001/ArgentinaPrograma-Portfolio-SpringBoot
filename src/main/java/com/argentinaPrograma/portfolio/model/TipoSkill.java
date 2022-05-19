/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.CascadeType;
import javax.persistence.Column;

import java.io.Serializable;
import java.util.List;


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
@Table(name="tiposkill")
public class TipoSkill implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    
    @Column(name="descripcion")
    private String descripcion;
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy="tipoSkill",cascade=CascadeType.ALL)
    private List<Skill> skills;
    

    @Override
    public String toString() {
        return "TipoSkill{" + "id=" + id + ", descripcion=" + descripcion + '}';
    }
    
    
}

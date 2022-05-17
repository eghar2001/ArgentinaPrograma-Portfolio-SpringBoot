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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author nahux
 */
@Entity
@Getter @Setter
@Table(name="skill")
public class Skill implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    
    @Column(name="descripcion")
    private String descripcion;
    
    @Column(name="porcentaje")
    private int porcentaje;
    
    @ManyToOne(cascade = {CascadeType.MERGE})
    @PrimaryKeyJoinColumn(name="perfil_id",referencedColumnName="id")
    private Perfil perfil;
    
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name="id_tipo_skill",referencedColumnName="id")
    private TipoSkill tipoSkill;

    @Override
    public String toString() {
        return "Skill{" + "id=" + id + ", descripcion=" + descripcion + ", porcentaje=" + porcentaje + ", perfil=" + perfil + ", tipoSkill=" + tipoSkill + '}';
    }
    
    
}

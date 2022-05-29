/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.security.entity;

import java.io.Serializable;
import java.util.regex.Pattern;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author nahux
 */
@Entity
@Table(name="validacionauth")
@Getter @Setter
public class ValidacionAuth implements Serializable {
    @Id
    private int id = 1;
    //user
    private int maxLengthUser;
    private Pattern regexUser;
    
    //pass
    private int maxLengthPass;
    private Pattern regexPass;
    
    //email
    private int maxLengthEmail;
    private Pattern regexEmail;
}

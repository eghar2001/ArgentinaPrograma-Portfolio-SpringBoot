/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.security.dto;

import java.util.regex.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author nahux
 */
@Getter @Setter
public class ValidacionesAuth {
    //user
   
    private int maxLengthUser = 30;
    private Pattern regexUser = Pattern.compile("^[a-z][.\\w]{4,}$");

    //pass
    private int maxLengthPass =40;
    private Pattern regexPass = Pattern.compile("^[a-zA-Z]{4,}[.\\d]+[\\w.]*$", Pattern.CASE_INSENSITIVE);

    //email
    private int maxLengthEmail = 60;
    private Pattern regexEmail = Pattern.compile("^[a-z][a-z.\\d]{3,}@(gmail|yahoo|ymail|outlook|hotmail)[.]com([.]ar)?$");
    
}   

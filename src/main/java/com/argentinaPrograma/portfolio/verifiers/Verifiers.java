/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.verifiers;

/**
 *
 * @author nahux
 */
public class Verifiers{
    
    /*    
    Metodo que se encarga de chequear que una string no sea nula ni contenta solamente espacios
    */
    
    public static boolean stringNoVacia(String str){
       return !(str.isBlank()||str.isEmpty()); 
    }
}

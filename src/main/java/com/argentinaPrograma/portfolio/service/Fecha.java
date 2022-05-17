/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.service;

import java.time.LocalDate;

import java.util.Arrays;


/**
 *
 * @author nahux
 */
//Servicio que contiene funciones que realizan operaciones con fechas
public class Fecha {
    
    private static  LocalDate fechaActual = LocalDate.now();
    private static void actualizaFechaActual(){
        fechaActual = LocalDate.now();
    }
    public static LocalDate getFechaActual(){
        actualizaFechaActual();
        return fechaActual;
    }
    
    /*
    Funcion que calcula la diferencia entre meses
    Solamente se usa como subfuncion de tiempotrabajado
    */
    private static int calculaMeses(int mesInicio,int mesFin){
            int resultado = 0;
            int mesActual = mesInicio;
            while(mesActual!=mesFin){
                mesActual = mesActual==12 ? 1:mesActual+1;                
                resultado++;                
            }
            return resultado;
        }  
    
    /*
    Funcion utilizada para 
    Retornar el periodo en el DTO de Experiencia
    
    USA FECHA ACTUAL
    */
    
    static String tiempoTrabajado(LocalDate fechaInicio,LocalDate fechaFin){
        actualizaFechaActual();        
        int aniosTrabajados;
        int mesesTrabajados;
        String returnStr = "";
        if (fechaFin==null){            
            if(fechaInicio.getMonthValue()<=fechaActual.getMonthValue()){
                aniosTrabajados = fechaActual.getYear()-fechaInicio.getYear();
            }
            else{
                aniosTrabajados = fechaActual.getYear()-fechaInicio.getYear()-1;
            }
            mesesTrabajados=Fecha.calculaMeses(fechaInicio.getMonthValue(),fechaActual.getMonthValue());           
        }        
        else{
            if (fechaInicio.getMonthValue()> fechaFin.getMonthValue()){
                aniosTrabajados = fechaFin.getYear()-fechaInicio.getYear()-1;
            }
            else{
                aniosTrabajados = fechaFin.getYear()-fechaInicio.getYear();
            }
            mesesTrabajados = Fecha.calculaMeses(fechaInicio.getMonthValue(),fechaFin.getMonthValue());
                      
        }
        String anio = aniosTrabajados == 1? "año":"años";
        String mes = mesesTrabajados == 1? "mes":"meses"; 
        if(mesesTrabajados ==0 && aniosTrabajados == 0){
            
        }
        else{
            if(aniosTrabajados == 0){
                returnStr+= mesesTrabajados +" "+ mes;
            }
            else if (mesesTrabajados == 0 ){
                returnStr += aniosTrabajados+" " + anio;
            }
            else{
                returnStr += aniosTrabajados + " " + anio + " "+ mesesTrabajados + " "+ mes;
            }
            
        }
        return returnStr;     
    }
    /*
    CANDIDATO A SER BORRADO
    
    Funcion que valida que 
    una fecha de nacimiento ingresada no sea mayor a la fecha actual
    
    USA FECHA ACTUAL
  
    
    
    public static boolean fechaNacimientoValida(LocalDate fechaNac){
        //Se hizo teniendo en consideracion que el OR es una fecha de cumpleaños
        actualizaFechaActual();
        if(fechaActual.getYear()>fechaNac.getYear()){
            return true;
        }
        else if (fechaActual.getYear()<fechaNac.getYear()){
            return false;
        }
        else{
            if (fechaActual.getMonthValue()>fechaNac.getMonthValue()){
                return true;
            }
            else if(fechaActual.getMonthValue()<fechaNac.getMonthValue()){
                return false;
            }
            else{
                return fechaActual.getDayOfMonth()>=fechaNac.getDayOfMonth();
            }
        }
    }
    */
    /*
    Funcion que calcula edad de una persona dada una fecha de nacimiento
    */
    public static int calculaEdad(LocalDate fechaNac){
        actualizaFechaActual();
        if (Fecha.fechaNacimientoValida(fechaNac)){
            if(fechaActual.getMonthValue()<fechaNac.getMonthValue() || (fechaActual.getMonthValue()==fechaNac.getMonthValue() && fechaActual.getDayOfMonth()< fechaNac.getDayOfMonth() ) ){
                return fechaActual.getYear() - fechaNac.getYear() - 1;
            }
            else{
                return fechaActual.getYear() - fechaNac.getYear(); 
            }
        }
        else{
            return -1;
        }
    }
    
    /*    
    Funcion de busqueda dicotomica que se usa en cantidadDias
    */
    private static boolean binarySearch(int[] numbers, int numberToSearch) {
    
  int size = numbers.length;  // Número de elementos
  int middle = size/2;        // Mitad del array
    
 
  if (numbers[middle] == numberToSearch)
    return true;
  else if (size == 1)
    return false;
  else if (numbers[middle] > numberToSearch)
    return binarySearch(Arrays.copyOfRange(numbers,0,middle),numberToSearch);
  else
    return binarySearch(Arrays.copyOfRange(numbers,middle+1,size),numberToSearch);    
}
    /*
    Funcion que calcula la cantidad de dias de un mes dependiendo del mes
    */
    public static int cantidadDias(int mes){
        int[] dias30 = {4,6,9,11};
        int[] dias31= {1,3,5,7,8,10,12};
        if (binarySearch(dias30,mes)){
            return 30;
        }
        else if (mes==2){
            return 28;
        }
        else if(binarySearch(dias31,mes)){
            return 31;
        }
        else{
            return 0;
        }
    }
    /*
    Funciones para evaluar que el dia y el mes ingresado no sea mayor
    al de la fecha actual
    
    */
    public static boolean mesValido(LocalDate fecha){
       actualizaFechaActual();
        if (fecha.getYear()<fechaActual.getYear()){
            return true;
        }
        else if(fecha.getYear()>fechaActual.getYear()){
            return false;
        }
        else{
            return fecha.getMonthValue()<= fechaActual.getMonthValue();
        }
    }
    public static boolean diaValido(LocalDate fecha){
        actualizaFechaActual();
        if (fecha.getYear()<fechaActual.getYear()){
            return true;
        }
        else if(fecha.getYear()>fechaActual.getYear()){
            return false;
        }
        else{
            if(fecha.getMonthValue() < fechaActual.getMonthValue()){
                return true;
            }
            else if(fecha.getMonthValue() > fechaActual.getMonthValue()){
                return false;
            }
            else{
                return fecha.getDayOfMonth() <= fechaActual.getDayOfMonth();
            }
        }
    }
     public static boolean fechaNacimientoValida(LocalDate fechaNac){
        //Se hizo teniendo en consideracion que el OR es una fecha de cumpleaños
        actualizaFechaActual();
        if(fechaActual.getYear()>fechaNac.getYear()){
            return true;
        }
        else if (fechaActual.getYear()<fechaNac.getYear()){
            return false;
        }
        else{
            if (fechaActual.getMonthValue()>fechaNac.getMonthValue()){
                return true;
            }
            else if(fechaActual.getMonthValue()<fechaNac.getMonthValue()){
                return false;
            }
            else{
                return fechaActual.getDayOfMonth()>=fechaNac.getDayOfMonth();
            }
        }
    }
    
}

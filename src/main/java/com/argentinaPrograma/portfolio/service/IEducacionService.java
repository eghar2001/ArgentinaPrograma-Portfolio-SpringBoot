/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.argentinaPrograma.portfolio.service;

import com.argentinaPrograma.portfolio.model.Educacion;
import java.util.List;

/**
 *
 * @author nahux
 */
public interface IEducacionService {
    
    public List<Educacion> getEducaciones();
    
    public Educacion getEducacionById(Long id);
    
    public List<Educacion> getEducacionByPerfAndTipo(Long id_perf,Long id_tipo);
    
    public Educacion saveEducacion(Educacion estudio);
    
    public void deleteEducacionById(Long id); 
    

    
   
}

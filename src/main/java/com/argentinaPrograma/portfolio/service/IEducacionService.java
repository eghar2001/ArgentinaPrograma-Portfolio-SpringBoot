/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.argentinaPrograma.portfolio.service;

import com.argentinaPrograma.portfolio.model.Educacion;
import com.argentinaPrograma.portfolio.model.TipoEducacion;
import java.util.List;

/**
 *
 * @author nahux
 */
public interface IEducacionService {
    /*
    Propios de educacion
    */
    public List<Educacion> getEducaciones();
    
    public Educacion getEducacionById(Long id);
    
    public List<Educacion> getEducacionByPerfAndTipo(Long id_perf,Long id_tipo);
    
    public Educacion saveEducacion(Educacion estudio);
    
    public void deleteEducacionById(Long id); 
    
    
    /*
    De tipo Educacion
    */

    public List<TipoEducacion> getTipoEstudios();
    
    public TipoEducacion getTipoEstudioById(Long id);
    
    public void saveTipoEstudio(TipoEducacion tipoEst);
   
}

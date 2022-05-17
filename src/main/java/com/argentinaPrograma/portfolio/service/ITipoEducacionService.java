/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.argentinaPrograma.portfolio.service;

import com.argentinaPrograma.portfolio.model.TipoEducacion;
import java.util.List;

/**
 *
 * @author nahux
 */
public interface ITipoEducacionService {
    
    public List<TipoEducacion> getTipoEstudios();
    
    public TipoEducacion getTipoEstudioById(Long id);
    
    public void saveTipoEstudio(TipoEducacion tipoEst);
    
    public void deleteTipoEstudio(Long id);
    
  
}

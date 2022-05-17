/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.argentinaPrograma.portfolio.service;

import com.argentinaPrograma.portfolio.model.Institucion;
import java.util.List;

/**
 *
 * @author nahux
 */
public interface IInstitucionService {
    public List<Institucion> getInstituciones();
    
    public Institucion getInstitucionById(Long id);
    
    public Institucion saveInstitucion(Institucion instiNueva);
    
    public void deleteInstitucionById(Long id);
    
    public Institucion getInstitucionByNombre(String nombre);
}

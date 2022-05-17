/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.argentinaPrograma.portfolio.service;

import com.argentinaPrograma.portfolio.model.Proyecto;
import java.util.List;

/**
 *
 * @author nahux
 */
public interface IProyectoService {
    public List<Proyecto> getProyectos();
    
    public void saveProyecto(Proyecto proyecto);
}

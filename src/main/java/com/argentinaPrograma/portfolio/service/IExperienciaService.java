/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.argentinaPrograma.portfolio.service;

import com.argentinaPrograma.portfolio.model.Experiencia;
import com.argentinaPrograma.portfolio.model.TipoJornada;
import java.util.List;

/**
 *
 * @author nahux
 */
public interface IExperienciaService {
    /*
    Propios de experiencia
    */
    public List<Experiencia> getExperiencias();
    
    public Experiencia getExperienciaById(Long id);
    
    public void deleteExperienciaById(Long id);
    
    public Experiencia saveExperiencia(Experiencia experiencia);
    
    /*
    De tipo jornada
    */
    public List<TipoJornada> getTiposJornada();
    
    public TipoJornada getTipoJornadaById(Long id);
    
    public void saveTipoJornada(TipoJornada tipoJornadaNueva);
}

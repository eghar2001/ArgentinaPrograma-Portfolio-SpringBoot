/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.argentinaPrograma.portfolio.service;

import com.argentinaPrograma.portfolio.model.TipoJornada;
import java.util.List;

/**
 *
 * @author nahux
 */
public interface ITipoJornadaService {
    public List<TipoJornada> getTiposJornada();
    
    public TipoJornada getTipoJornadaById(Long id);
    
    public void saveTipoJornada(TipoJornada tipoJornadaNueva);
}

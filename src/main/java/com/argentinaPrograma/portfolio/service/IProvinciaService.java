/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.argentinaPrograma.portfolio.service;

import com.argentinaPrograma.portfolio.model.Provincia;
import java.util.List;

/**
 *
 * @author nahux
 */
public interface IProvinciaService {
    public List<Provincia> getProvincias();
    
    public void saveProvincia(Provincia provin);
    
    public Provincia getProvinciaById(Long id);
    
    public void deleteProv(Long id);

 
    
    
    public Provincia provPorNombre(String provincia);
    
    
}

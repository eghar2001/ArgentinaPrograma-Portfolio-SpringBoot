/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.argentinaPrograma.portfolio.service;

import com.argentinaPrograma.portfolio.dto.LocalidadDto;
import com.argentinaPrograma.portfolio.model.Localidad;
import com.argentinaPrograma.portfolio.model.Provincia;
import java.util.List;

/**
 *
 * @author nahux
 */

public interface ILocalidadService {
    
    /*
    Propios de Localidad
    */
    public void saveLocalidad(Localidad loc);
    
    public void deleteLocalidad(Long id);
    

    
    public Localidad getLocalidadByCodPostal(Long codPostal);
    
    public List<Localidad> getLocalidades();
    
    public boolean existeLocalidad(Long codPostal);
    
    public Localidad getLocByNombreAndProv(String localidad, Long id_provincia);
    
    
    /*
    Los de provincis
    */
    public List<Provincia> getProvincias();
    
    public void saveProvincia(Provincia provin);
    
    public Provincia getProvinciaById(Long id);
    
    public void deleteProv(Long id);    
    
    public Provincia provPorNombre(String provincia);
    
}

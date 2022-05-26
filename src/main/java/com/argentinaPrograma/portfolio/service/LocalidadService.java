/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.service;

import com.argentinaPrograma.portfolio.dto.LocalidadDto;
import com.argentinaPrograma.portfolio.model.Localidad;
import com.argentinaPrograma.portfolio.model.Provincia;
import com.argentinaPrograma.portfolio.repository.LocalidadRepository;
import com.argentinaPrograma.portfolio.repository.ProvinciaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nahux
 */
@Service
public class LocalidadService implements ILocalidadService {
    @Autowired
    private LocalidadRepository localidadRepo;
    
    @Autowired
    private ProvinciaRepository provinRepo;
    

   
    /*
    Los propios de Localidad
    */
    @Override
    public void saveLocalidad(Localidad loc){
        this.localidadRepo.save(loc);
    }
    
    @Override
    public void deleteLocalidad(Long id){
        this.localidadRepo.deleteById(id);
    }

    
    @Override
    public Localidad getLocalidadByCodPostal(Long codPostal){
        return this.localidadRepo.findById(codPostal).orElse(null);
    }
    
    @Override
    public List<Localidad> getLocalidades(){
        return this.localidadRepo.findAll();
    }
    
    @Override
    public boolean existeLocalidad(Long codPostal){
        return this.localidadRepo.existsById(codPostal);
    }
    
    @Override
    public Localidad getLocByNombreAndProv(String localidad, Long id_provincia){
        return this.localidadRepo.getLocByNombreAndProv(localidad, id_provincia).orElse(null);
    }
    
    
    /*
    Los propios de Provincia
    */
    
  
    
    @Override
    public List<Provincia> getProvincias(){
        return this.provinRepo.findAll();
    }
    
    @Override
    public void saveProvincia(Provincia provin){
        this.provinRepo.save(provin);
    }
    
    @Override
    public Provincia getProvinciaById(Long id){
        return this.provinRepo.findById(id).orElse(null);
    }
    
    @Override
    public void deleteProv(Long id){  
        this.provinRepo.deleteById(id);
    }   

    @Override
    public Provincia provPorNombre(String provincia){
        return this.provinRepo.getProvByNombre(provincia).orElse(null);
    }
}

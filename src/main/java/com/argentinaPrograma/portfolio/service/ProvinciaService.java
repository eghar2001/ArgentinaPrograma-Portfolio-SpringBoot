/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.service;


import com.argentinaPrograma.portfolio.model.Provincia;

import com.argentinaPrograma.portfolio.repository.ProvinciaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nahux
 */
@Service
public class ProvinciaService implements IProvinciaService {
    @Autowired
    private ProvinciaRepository provinRepo;
    

    
  
    
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

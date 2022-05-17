/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.service;

import com.argentinaPrograma.portfolio.model.Institucion;
import com.argentinaPrograma.portfolio.repository.InstitucionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author nahux
 */
@Service
@Transactional
public class InstitucionService implements IInstitucionService {
    @Autowired
    private InstitucionRepository instiRepo;
    
    @Override
    public List<Institucion> getInstituciones(){
        return this.instiRepo.findAll();
    }
    
    @Override
    public Institucion getInstitucionById(Long id){
        return this.instiRepo.findById(id).orElse(null);
    }
    
    @Override
    public Institucion saveInstitucion(Institucion instiNueva){
        return this.instiRepo.save(instiNueva);
    }
    
    @Override
    public void deleteInstitucionById(Long id){
        this.instiRepo.deleteById(id);
    }
    
    @Override
    public Institucion getInstitucionByNombre(String nombre){
        return this.instiRepo.getInstitucionByNombre(nombre).orElse(null);
    }
    
}

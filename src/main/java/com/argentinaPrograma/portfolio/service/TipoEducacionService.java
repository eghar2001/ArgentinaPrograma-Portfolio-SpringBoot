/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.service;

import com.argentinaPrograma.portfolio.model.TipoEducacion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.argentinaPrograma.portfolio.repository.TipoEducacionRepository;



/**
 *
 * @author nahux
 */

@Service
public class TipoEducacionService implements ITipoEducacionService{
    @Autowired
    private TipoEducacionRepository tipoEstRepo;
    
    
    
    @Override
    public List<TipoEducacion> getTipoEstudios(){
        return this.tipoEstRepo.findAll();
    }
    
    @Override
    public TipoEducacion getTipoEstudioById(Long id){
        return this.tipoEstRepo.findById(id).orElse(null);
    }
    
    @Override
    public void saveTipoEstudio(TipoEducacion tipoEst){
        this.tipoEstRepo.save(tipoEst);
    }
    
    @Override
    public void deleteTipoEstudio(Long id){
       
        this.tipoEstRepo.deleteById(id);
    }
}

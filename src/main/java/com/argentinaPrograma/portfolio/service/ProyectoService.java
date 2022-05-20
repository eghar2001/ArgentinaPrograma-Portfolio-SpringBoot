/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.service;

import com.argentinaPrograma.portfolio.model.Proyecto;
import com.argentinaPrograma.portfolio.repository.ProyectoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nahux
 */
@Service
public class ProyectoService implements IProyectoService{
    @Autowired
    private ProyectoRepository proyRepo;
    
    @Override
    public List<Proyecto> getProyectos(){
        return this.proyRepo.findAll();
    }
    
    @Override
    public Proyecto saveProyecto(Proyecto proyecto){
        return this.proyRepo.save(proyecto);
    }
    
    @Override
    public void deleteProyecto(Long id){
        this.proyRepo.deleteById(id);
        
    }
    
    @Override
    public Proyecto findById(Long id){
        return this.proyRepo.findById(id).orElse(null);
    }
}

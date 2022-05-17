/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.service;

import com.argentinaPrograma.portfolio.model.Educacion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.argentinaPrograma.portfolio.repository.EducacionRepository;

/**
 *
 * @author nahux
 */
@Transactional
@Service
public class EducacionService implements IEducacionService {
    @Autowired
    private EducacionRepository educacionRepo;
    
    @Override
    public List<Educacion> getEducaciones(){
        return this.educacionRepo.findAll();
    }
    
    @Override
    public Educacion getEducacionById(Long id){
        return this.educacionRepo.findById(id).orElse(null);
    }
    @Override
    public List<Educacion> getEducacionByPerfAndTipo(Long id_perf,Long id_tipo){
        return this.educacionRepo.getEducacionByPerfAndTipo(id_perf, id_tipo);
    }
    
    @Override
    public Educacion saveEducacion(Educacion estudio){
        return this.educacionRepo.save(estudio);
    }
    
    @Override
    public void deleteEducacionById(Long id){
        this.educacionRepo.deleteById(id);
    }
    
    
    
    
}

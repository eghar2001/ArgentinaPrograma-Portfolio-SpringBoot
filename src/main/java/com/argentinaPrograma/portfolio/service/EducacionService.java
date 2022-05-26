/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.service;

import com.argentinaPrograma.portfolio.model.Educacion;
import com.argentinaPrograma.portfolio.model.TipoEducacion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.argentinaPrograma.portfolio.repository.EducacionRepository;
import com.argentinaPrograma.portfolio.repository.TipoEducacionRepository;

/**
 *
 * @author nahux
 */
@Transactional
@Service
public class EducacionService implements IEducacionService {
    @Autowired
    private EducacionRepository educacionRepo;
    
    @Autowired
    private TipoEducacionRepository tipoEstRepo;
    /*
    Propios de Educacion
    */
    
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
    
    /*
    De tipo educacion
    */
    
    
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
    
    
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.service;

import com.argentinaPrograma.portfolio.model.Experiencia;
import com.argentinaPrograma.portfolio.model.TipoJornada;
import com.argentinaPrograma.portfolio.repository.ExperienciaRepository;
import com.argentinaPrograma.portfolio.repository.TipoJornadaRepository;
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
public class ExperienciaService implements IExperienciaService{
    @Autowired
    private ExperienciaRepository experienciaRepo;
    
     @Autowired
    private TipoJornadaRepository tipoJornadaRepo;
    
    /*
     Metodos propios de experiencia
     */
    @Override
    public List<Experiencia> getExperiencias(){
       return this.experienciaRepo.findAll();
    }
    
    @Override
    public Experiencia getExperienciaById(Long id){
        return this.experienciaRepo.findById(id).orElse(null);
    }
    
    @Override
    public void deleteExperienciaById(Long id){
        this.experienciaRepo.deleteById(id);
    }
    
    @Override
    public Experiencia saveExperiencia(Experiencia experiencia){
        return this.experienciaRepo.save(experiencia);
    }
   /*
    Metodos propios de TipoJornada
    */
    @Override
    public List<TipoJornada> getTiposJornada(){
       return this.tipoJornadaRepo.findAll();
    }
    
    @Override
    public void saveTipoJornada(TipoJornada tipoJornadaNueva){
        this.tipoJornadaRepo.save(tipoJornadaNueva);
    }
    
    @Override
    public TipoJornada getTipoJornadaById(Long id){
        return this.tipoJornadaRepo.findById(id).orElse(null);
    }
}

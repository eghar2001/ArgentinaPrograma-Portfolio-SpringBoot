/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.controller;

import com.argentinaPrograma.portfolio.dto.ExperienciaDto;
import com.argentinaPrograma.portfolio.model.Experiencia;
import com.argentinaPrograma.portfolio.model.Institucion;
import com.argentinaPrograma.portfolio.model.Perfil;
import com.argentinaPrograma.portfolio.model.TipoJornada;
import com.argentinaPrograma.portfolio.service.IExperienciaService;
import com.argentinaPrograma.portfolio.service.IInstitucionService;
import com.argentinaPrograma.portfolio.service.IPerfilService;
import com.argentinaPrograma.portfolio.service.PasaADto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nahux
 */
@RestController
@CrossOrigin(origins ={"https://miportfolioanguar.web.app","http://localhost:4200"})
@RequestMapping("/experiencia")
public class ExperienciaController {
    @Autowired
    private IExperienciaService experienciaServ;
    
    @Autowired
    private IPerfilService perfServ;
    
    @Autowired
    private IInstitucionService instiServ;
    
   
    /*
    Endpoints propios de Experienca
    */
    @GetMapping("/traer")
    @ResponseBody
    public List<Experiencia> getExperiencias(){
        return this.experienciaServ.getExperiencias();
    }
    
    @GetMapping("/traer/{id}")
    @ResponseBody
    public ExperienciaDto getExperienciaById(@PathVariable Long id){
        Experiencia exp =  this.experienciaServ.getExperienciaById(id);
        return PasaADto.experiencia(exp);
    }
    /*
    Obiene un dto, lo mapea a las entidades del modelo, y devuelve el dto denuevo
    */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crear")
    public ExperienciaDto createExperiencia(@RequestBody ExperienciaDto expDto){       
        Experiencia savedExp = dtoAExperiencia(expDto);    
        savedExp = this.experienciaServ.saveExperiencia(savedExp);
        return PasaADto.experiencia(savedExp);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/borrar/{id}")
    public void deleteExperiencia(@PathVariable Long id){
        this.experienciaServ.deleteExperienciaById(id);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/editar")
    public ExperienciaDto editExperiencia( @RequestBody ExperienciaDto edittedExp){      
        Experiencia savedExp = dtoAExperiencia(edittedExp);
        savedExp = this.experienciaServ.saveExperiencia(savedExp);
        return PasaADto.experiencia(savedExp);
     
    }
    
    private Experiencia dtoAExperiencia(ExperienciaDto expDto){
        Experiencia exp = new Experiencia();
        if(expDto.getId() != null ){
            exp.setId(expDto.getId());
        }
        exp.setCargo(expDto.getCargo());
        exp.setFechaDesde(expDto.getFechaDesde());
        exp.setFechaHasta(expDto.getFechaHasta());
        exp.setFondoUrl(expDto.getFondoUrl());
        
        /*
        Atributos que son entidades
        */
        Institucion insti = instiServ.getInstitucionByNombre(expDto.getNombreInstitucion());
        if(insti==null){
            insti = new Institucion();
            insti.setNombre(expDto.getNombreInstitucion());
            instiServ.saveInstitucion(insti);
        }
        exp.setInstitucion(insti);
        Perfil perf = this.perfServ.getPerfilById(expDto.getIdPerfil());
        exp.setPerfil(perf); 
              
        
        TipoJornada tipoJorn = this.experienciaServ.getTipoJornadaById(expDto.getTipoJornada().getId());
        exp.setTipoJornada(tipoJorn);
        return exp;
    }
    
    /*
    Endpoints de TipoJornada
    */
    @GetMapping("/tipoJornada/traer")
    @ResponseBody
    public List<TipoJornada> getTiposJornada(){
       return this.experienciaServ.getTiposJornada();
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/tipoJornada/crear")
    public void createTipoJornada(@RequestBody TipoJornada tipoJornadaNueva){
        this.experienciaServ.saveTipoJornada(tipoJornadaNueva);
    }
    
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.controller;

import com.argentinaPrograma.portfolio.dto.EducacionByTipoDto;
import com.argentinaPrograma.portfolio.dto.EducacionDto;
import com.argentinaPrograma.portfolio.dto.ExperienciaDto;
import com.argentinaPrograma.portfolio.dto.PortfolioDto;
import com.argentinaPrograma.portfolio.dto.ProyectoDto;
import com.argentinaPrograma.portfolio.dto.SkillDto;
import com.argentinaPrograma.portfolio.dto.SkillsByTipoDto;
import com.argentinaPrograma.portfolio.model.Educacion;
import com.argentinaPrograma.portfolio.model.Experiencia;
import com.argentinaPrograma.portfolio.model.Perfil;
import com.argentinaPrograma.portfolio.model.Proyecto;
import com.argentinaPrograma.portfolio.model.Skill;
import com.argentinaPrograma.portfolio.model.TipoEducacion;
import com.argentinaPrograma.portfolio.model.TipoSkill;

import com.argentinaPrograma.portfolio.service.IEducacionService;
import com.argentinaPrograma.portfolio.service.IPerfilService;
import com.argentinaPrograma.portfolio.service.ITipoEducacionService;
import com.argentinaPrograma.portfolio.service.ITipoSkillService;
import com.argentinaPrograma.portfolio.service.PasaADto;
import static com.argentinaPrograma.portfolio.service.PasaADto.experiencia;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nahux
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/portfolio")
public class PortfolioController {
    @Autowired
    private IPerfilService perfilServ;
    
    @Autowired
    private ITipoEducacionService tipoEduServ;
    
    @Autowired 
    private IEducacionService eduServ;
    
    @Autowired
    private ITipoSkillService tipoSkillServ;
    
    @GetMapping("/traer/{id_perfil}")
    @ResponseBody
    public PortfolioDto getPortfolio(@PathVariable Long id_perfil){
        
        PortfolioDto portfolio = new PortfolioDto();
        //Asigno Perfil
        Perfil perfil = this.perfilServ.getPerfilById(id_perfil);
        portfolio.setPerfil(PasaADto.perfil(perfil));   
        
        /*
        Asigno listado de educaciones by tipo
        */       
        List<EducacionDto> educacionesDto = new ArrayList<>();
        for(Educacion edu:perfil.getEducaciones()){
            educacionesDto.add(PasaADto.educacion(edu));
        }
        portfolio.setEducaciones(educacionesDto);
        
        /*
        Agrego Experiencias        
        */
        List<ExperienciaDto> experiencias = new ArrayList<>();
        for(Experiencia exp:perfil.getExperiencias()){
            experiencias.add(experiencia(exp));
        }
        portfolio.setExperiencias(experiencias);
        
        /*
        Agrego Skills
        */
       List<SkillDto> skillsDto = new ArrayList<>();
       for(Skill skill:perfil.getSkills()){
           skillsDto.add(PasaADto.skill(skill));
       }
       portfolio.setSkills(skillsDto);
        
       /*
       Agrego Proyectos
       */
       List<ProyectoDto> proysDto = new ArrayList<>();
       for(Proyecto proy:perfil.getProyectos()){
           ProyectoDto proyDto = PasaADto.proyecto(proy);
           proysDto.add(proyDto);
          
       }
       portfolio.setProyectos(proysDto);
        
       
       return portfolio;
    } 
}

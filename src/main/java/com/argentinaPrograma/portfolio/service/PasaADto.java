/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.service;


import com.argentinaPrograma.portfolio.dto.*;
import com.argentinaPrograma.portfolio.model.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 *
 * @author nahux
 */
@Service
public class PasaADto {
    /*
    Servicio que contiene funciones estaticas que pasan a dto cada tipo de dto
        Solamente tiene los datos utiles para el manejo en el frontend
    */

    public static ProyectoDto proyecto(Proyecto proy){
        ProyectoDto proyDto = new ProyectoDto();
        proyDto.setId(proy.getId());
        proyDto.setNombre(proy.getNombre());
        proyDto.setDescripcion(proy.getDescripcion());
        proyDto.setIdPerfil(proy.getPerfil().getUsuario().getId());
        proyDto.setUrl(proy.getUrl());
        proyDto.setFondoUrl(proy.getFondoUrl());
        return proyDto;
    }
    public static SkillDto skill (Skill skillOrig){
        SkillDto skillDto = new SkillDto();
        skillDto.setId(skillOrig.getId());
        skillDto.setDescripcion(skillOrig.getDescripcion());
        skillDto.setPorcentaje(skillOrig.getPorcentaje());
        skillDto.setIdTipoSkill(skillOrig.getTipoSkill().getId());
        return skillDto;
    }
    public static PerfilDto perfil(Perfil perf){
        PerfilDto perfDto = new PerfilDto();
        perfDto.setId(perf.getUsuario_id());
        perfDto.setNombre(perf.getNombre());
        perfDto.setApellido(perf.getApellido());
        perfDto.setLocalidad(perf.getLocalidad().getNombre());
        perfDto.setAbout(perf.getAbout());
        perfDto.setProvincia(perf.getLocalidad().getProvincia().getNombre());
        perfDto.setFechaNac(perf.getFechaNac());
        perfDto.setProfesion(perf.getProfesion());
        perfDto.setEdad(Fecha.calculaEdad(perf.getFechaNac()));        
        perfDto.setPerfilUrl(perf.getPerfilUrl());
        perfDto.setBannerUrl(perf.getBannerUrl());
        
     
        
        /*
        Agrego Redes Sociales
        */
        if(perf.getRedesSociales()!=null){
            List<RedPerfilDto> redesSociales = new ArrayList<>();
            for(Perfil_has_RedSocial red:perf.getRedesSociales()){
                redesSociales.add(redPerfil(red));
            }
            perfDto.setRedesSociales(redesSociales);
        }
        return perfDto;
    }
    public static EducacionDto educacion(Educacion edu){
        EducacionDto eduDto = new EducacionDto();
        eduDto.setId(edu.getId());
        eduDto.setIdPerfil(edu.getPerfil().getUsuario_id());
        eduDto.setFechaDesde(edu.getFechaDesde());
        eduDto.setFechaHasta(edu.getFechaHasta());
        eduDto.setDescripcion(edu.getDescripcion());          
        eduDto.setNombreInstitucion(edu.getInstitucion().getNombre());
        eduDto.setFotoInstitucionUrl(edu.getInstitucion().getLogoUrl());
        eduDto.setIdTipoEdu(edu.getTipoEducacion().getId());
        return eduDto;
    }
  
    public static ExperienciaDto experiencia(Experiencia exp){
        ExperienciaDto expDto = new ExperienciaDto();
        expDto.setId(exp.getId());
        expDto.setIdPerfil(exp.getPerfil().getUsuario().getId());
        expDto.setNombreInstitucion(exp.getInstitucion().getNombre());
        expDto.setTipoJornada(exp.getTipoJornada());
        expDto.setFechaDesde(exp.getFechaDesde());
        expDto.setFechaHasta(exp.getFechaHasta());
        expDto.setPeriodo(Fecha.tiempoTrabajado(expDto.getFechaDesde(), expDto.getFechaHasta()));
        expDto.setCargo(exp.getCargo());
        expDto.setFondoUrl(exp.getFondoUrl());
        
        return expDto;
        
    }
    public static RedPerfilDto redPerfil(Perfil_has_RedSocial perfil_red){
        RedPerfilDto redDto = new RedPerfilDto();
        redDto.setRedSocial(perfil_red.getRedSocial());
        redDto.setUrl(perfil_red.getRedSocialUrl());
        return redDto;
    }
    
    
    
    
    public static LocalidadDto localidad(Localidad loc){
        LocalidadDto locDto = new LocalidadDto();
        locDto.setLocalidad(loc.getNombre());
        locDto.setProvincia(loc.getProvincia().getNombre());
        return locDto;
    }
    
    
   

    
}

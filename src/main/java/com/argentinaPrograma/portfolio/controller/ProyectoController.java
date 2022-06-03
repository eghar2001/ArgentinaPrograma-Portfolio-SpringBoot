/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.controller;

import com.argentinaPrograma.portfolio.dto.ProyectoDto;
import com.argentinaPrograma.portfolio.model.Perfil;
import com.argentinaPrograma.portfolio.model.Proyecto;
import com.argentinaPrograma.portfolio.service.IPerfilService;
import com.argentinaPrograma.portfolio.service.IProyectoService;
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
@CrossOrigin(origins = {"https://miportfolioanguar.web.app","http://localhost:4200"})
@RequestMapping("/proyecto")
public class ProyectoController {
    @Autowired
    private IProyectoService proyServ;
    
    @Autowired 
    private IPerfilService perfServ;
    
    @GetMapping("/traer")
    @ResponseBody
    public List<Proyecto> getProyectos(){
        return this.proyServ.getProyectos();
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crear")
    @ResponseBody
    public ProyectoDto createProyecto(@RequestBody ProyectoDto proyDto){
        Proyecto savedProy = new Proyecto();
        savedProy.setNombre(proyDto.getNombre());
        savedProy.setDescripcion(proyDto.getDescripcion());
        savedProy.setUrl(proyDto.getUrl());
        savedProy.setFondoUrl(proyDto.getFondoUrl());
        
        /*
        Atributo dependiente
        */
        Perfil perf = this.perfServ.getPerfilById(proyDto.getIdPerfil());
        savedProy.setPerfil(perf);
        System.out.println(savedProy.getPerfil().getUsuario_id());
        
        savedProy = this.proyServ.saveProyecto(savedProy);
        
        return PasaADto.proyecto(savedProy);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/editar")
    @ResponseBody
    public ProyectoDto editProyecto(@RequestBody ProyectoDto proyDto){
        Proyecto savedProy = this.proyServ.findById(proyDto.getId());        
        savedProy.setNombre(proyDto.getNombre());
        savedProy.setDescripcion(proyDto.getDescripcion());
        savedProy.setUrl(proyDto.getUrl());
        savedProy.setFondoUrl(proyDto.getFondoUrl());
        
        
        
        savedProy = this.proyServ.saveProyecto(savedProy);
        
        return PasaADto.proyecto(savedProy);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/borrar/{id}")
    public void deleteProyecto(@PathVariable Long id){
        this.proyServ.deleteProyecto(id);
    }
}

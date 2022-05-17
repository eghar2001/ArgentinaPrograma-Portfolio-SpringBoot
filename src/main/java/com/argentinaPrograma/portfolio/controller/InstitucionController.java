/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.controller;

import com.argentinaPrograma.portfolio.model.Institucion;
import com.argentinaPrograma.portfolio.service.IInstitucionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nahux
 */
@RestController
@RequestMapping("/institucion")
public class InstitucionController {
    @Autowired
    private IInstitucionService instiServ;
    
    @GetMapping("/traer")
    @ResponseBody
    public List<Institucion> getInstiticiones(){
        return this.instiServ.getInstituciones();
    }
    
    @GetMapping("/traer/{id}")
    @ResponseBody
    public Institucion getInstitucionById(@PathVariable Long id){
        return this.instiServ.getInstitucionById(id);
    }
    
    
    @PostMapping("/crear")
    public void createInstitucion(@RequestBody Institucion instiNueva){
        this.instiServ.saveInstitucion(instiNueva);
    }
    
    @DeleteMapping("/borrar/{id}")
    public void deleteInstitucionById(@PathVariable Long id){
        this.instiServ.deleteInstitucionById(id);
    }
    
    
}

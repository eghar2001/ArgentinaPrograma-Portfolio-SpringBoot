/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.controller;


import com.argentinaPrograma.portfolio.model.RedSocial;
import com.argentinaPrograma.portfolio.service.IRedSocialService;

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
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/redSocial")
public class RedSocialController {
    @Autowired
    private IRedSocialService redSocialServ;
    
    @GetMapping("/traer")
    @ResponseBody
    public List<RedSocial> getRedesSociales(){
       
        return this.redSocialServ.getRedesSociales();
        
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/traer/redesFaltantes/{idPerfil}")
    public List<RedSocial> getRedesFaltantes(@PathVariable Long idPerfil){
        return this.redSocialServ.getRedesFaltantes(idPerfil);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crear")
    public void createRedSocial(@RequestBody RedSocial redSocialNueva){
        this.redSocialServ.saveRedSocial(redSocialNueva);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/editar/{id}")  
    public void editRedSocial(@RequestBody RedSocial edittedRedSocial,@PathVariable Long id){
        RedSocial originalRedSocial = this.redSocialServ.getRedSocialById(id);
        originalRedSocial.setNombre(edittedRedSocial.getNombre());
        originalRedSocial.setClaseBoxIcon(edittedRedSocial.getClaseBoxIcon());
        originalRedSocial.setColor(edittedRedSocial.getColor());
        this.redSocialServ.saveRedSocial(originalRedSocial);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/borrar/{id}")
    public void deleteRedSocial(@PathVariable Long id){
        
        this.redSocialServ.deleteRedSocialById(id);
    }
}

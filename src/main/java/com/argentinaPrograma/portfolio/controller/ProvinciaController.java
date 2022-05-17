/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.controller;


import com.argentinaPrograma.portfolio.model.Provincia;

import com.argentinaPrograma.portfolio.service.IProvinciaService;
import com.argentinaPrograma.portfolio.verifiers.Verifiers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/provincia")
public class ProvinciaController {
    @Autowired
    private IProvinciaService provinServ;
    
    

    
    @GetMapping("/traer")
    @ResponseBody
    public List<Provincia> getProvins(){
        return this.provinServ.getProvincias();
    }
    
    @GetMapping("/traer/{id}")
    @ResponseBody
    public Provincia getProvinById(@PathVariable Long id){
        return this.provinServ.getProvinciaById(id);
    }
    
    
    @PostMapping("/crear")
    public void createProvin(@RequestBody Provincia provin){    
       
        this.provinServ.saveProvincia(provin);
    }
    
    @DeleteMapping("/borrar/{id}")
    public void deleteProvin(@PathVariable Long id){
        this.provinServ.deleteProv(id);
    }
    
 
    
    @PutMapping("/editar/{id}")
    public void editarProvin(@PathVariable Long id, @RequestBody Provincia edittedProvin){
        Provincia returnProvin = this.provinServ.getProvinciaById(id);
        String edittedNombre = edittedProvin.getNombre();
        if(Verifiers.stringNoVacia(edittedNombre)){
            returnProvin.setNombre(edittedNombre);
        }
        
        
        this.provinServ.saveProvincia(returnProvin);
    }
    
    
}

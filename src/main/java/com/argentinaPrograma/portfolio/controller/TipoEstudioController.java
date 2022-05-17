/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.controller;

import com.argentinaPrograma.portfolio.model.TipoEducacion;
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
import com.argentinaPrograma.portfolio.service.ITipoEducacionService;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 *
 * @author nahux
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/educacion/tipo")
public class TipoEstudioController {
    @Autowired
    private ITipoEducacionService tipoEstServ;
    
    @GetMapping("/traer")
    @ResponseBody
    public List<TipoEducacion> getTipoEstudios(){
        return this.tipoEstServ.getTipoEstudios();
    }
    
    @GetMapping("/traer/{id}")
    @ResponseBody
    public TipoEducacion getTipoEstudioById(@PathVariable Long id){
        return this.tipoEstServ.getTipoEstudioById(id);
    }
    
    @PostMapping("/crear")
    public void createTipoEstudio(@RequestBody TipoEducacion tipoEst){
        this.tipoEstServ.saveTipoEstudio(tipoEst);
    }
    
    @DeleteMapping("/borrar/{id}")
    public void deleteTipoEstudio(@PathVariable Long id){
        this.tipoEstServ.deleteTipoEstudio(id);
    }
    
    @PutMapping("/editar/{id}")
    public void editarTipoEstudio(@PathVariable Long id, @RequestBody TipoEducacion edittedTipoEst){
        TipoEducacion returnTipoEst = this.tipoEstServ.getTipoEstudioById(id);
        String edittedDesc = edittedTipoEst.getDescripcion();        
        if(Verifiers.stringNoVacia(edittedDesc)){
            returnTipoEst.setDescripcion(edittedDesc);
        }
        this.tipoEstServ.saveTipoEstudio(returnTipoEst);
    }
    
}

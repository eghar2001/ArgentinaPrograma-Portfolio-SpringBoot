/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.controller;

import com.argentinaPrograma.portfolio.model.TipoSkill;
import com.argentinaPrograma.portfolio.service.ITipoSkillService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/skill/tipo")
public class TipoSkillController {
    @Autowired
    private ITipoSkillService tipoSkillServ;
    
    @GetMapping("/traer")
    @ResponseBody
    public List<TipoSkill> getTiposSkill(){
        return this.tipoSkillServ.getTiposSkill();
    }
    
    @PostMapping("/crear")
    public void createTipoSkill(@RequestBody TipoSkill tipoSkill){
        this.tipoSkillServ.saveTipoSkill(tipoSkill);
    }
    
    @DeleteMapping("/borrar/{id}")
    public void deleteTipoSkill(@PathVariable Long id){
        this.tipoSkillServ.deleteTipoSkillById(id);
    }
    
    
}

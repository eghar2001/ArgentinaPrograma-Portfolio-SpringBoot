/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.controller;

import com.argentinaPrograma.portfolio.model.Skill;
import com.argentinaPrograma.portfolio.service.ISkillService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/skill")
public class SkillController {
    @Autowired
    private ISkillService skillServ;
    
    
    @GetMapping("/traer")
    @ResponseBody
    public List<Skill> getSkills(){
        return this.skillServ.getSkills();
    }
    
    @PostMapping("/crear")
    public void createSkill(@RequestBody Skill skill){
        this.skillServ.saveSkill(skill);
    }
    
    @DeleteMapping("/borrar/{id}")
    public void deleteSkill(Long id){
        this.skillServ.deleteSkillById(id);
    }
    
    @PutMapping("/editar")
    public void editSkill(@RequestBody Skill edittedSkill){
        this.skillServ.saveSkill(edittedSkill);
    }
}

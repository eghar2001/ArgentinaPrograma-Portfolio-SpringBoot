/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.service;

import com.argentinaPrograma.portfolio.model.Skill;
import com.argentinaPrograma.portfolio.repository.SkillRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nahux
 */
@Service
public class SkillService  implements ISkillService{
    @Autowired
    private SkillRepository skillRepo;
    
    @Override
    public List<Skill> getSkills(){
        return this.skillRepo.findAll();
    }
    
    @Override
    public Skill getSkillById(Long id){
        return this.skillRepo.findById(id).orElse(null);
    }
    
    @Override
    public Skill saveSkill(Skill skill){
        return this.skillRepo.save(skill);
    }
    
    @Override
    public void deleteSkillById(Long id){
        this.skillRepo.deleteById(id);
    }
    
    
}

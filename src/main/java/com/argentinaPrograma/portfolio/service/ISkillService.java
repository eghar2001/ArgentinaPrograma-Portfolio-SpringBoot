/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.argentinaPrograma.portfolio.service;

import com.argentinaPrograma.portfolio.model.Skill;
import java.util.List;

/**
 *
 * @author nahux
 */
public interface ISkillService {
    public List<Skill> getSkills();
    
    public Skill getSkillById(Long id);
    
    public void saveSkill(Skill skill);
    
    public void deleteSkillById(Long id);
    
}

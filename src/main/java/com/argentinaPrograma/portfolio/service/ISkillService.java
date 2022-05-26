/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.argentinaPrograma.portfolio.service;

import com.argentinaPrograma.portfolio.model.Skill;
import com.argentinaPrograma.portfolio.model.TipoSkill;
import java.util.List;

/**
 *
 * @author nahux
 */
public interface ISkillService {
    
    /*
    Los de skill
    */
    public List<Skill> getSkills();
    
    public Skill getSkillById(Long id);
    
    public Skill saveSkill(Skill skill);
    
    public void deleteSkillById(Long id);
    
    /*
    Los de tipoSkill
    */
    public List<TipoSkill> getTiposSkill();
    
    public TipoSkill getTipoSkillById(Long id);
    
    public void saveTipoSkill(TipoSkill tipoSkill);
}

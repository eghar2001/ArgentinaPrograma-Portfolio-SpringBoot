/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.service;

import com.argentinaPrograma.portfolio.model.TipoSkill;
import com.argentinaPrograma.portfolio.repository.TipoSkillRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nahux
 */
@Service
public class TipoSkillService implements ITipoSkillService{
    @Autowired
    private TipoSkillRepository tipoSkillRepo;
    
    @Override
    public List<TipoSkill> getTiposSkill(){
        return this.tipoSkillRepo.findAll();
    }
    
    @Override
    public TipoSkill getTipoSkillById(Long id){
        return this.tipoSkillRepo.findById(id).orElse(null);
    }
    
    @Override
    public void saveTipoSkill(TipoSkill tipoSkill){
        this.tipoSkillRepo.save(tipoSkill);
                
    }
    
    @Override
    public void deleteTipoSkillById(Long id){
        this.tipoSkillRepo.deleteById(id);
    }
}

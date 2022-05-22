/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.controller;

import com.argentinaPrograma.portfolio.model.Skill;
import com.argentinaPrograma.portfolio.service.ISkillService;
import java.util.List;
import com.argentinaPrograma.portfolio.dto.SkillDto;
import com.argentinaPrograma.portfolio.model.Perfil;
import com.argentinaPrograma.portfolio.model.TipoSkill;
import com.argentinaPrograma.portfolio.service.IPerfilService;
import com.argentinaPrograma.portfolio.service.ITipoSkillService;
import com.argentinaPrograma.portfolio.service.PasaADto;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/skill")
public class SkillController {
    @Autowired
    private ISkillService skillServ;
    
    @Autowired
    private IPerfilService perfilServ;
    
    @Autowired
    private ITipoSkillService tipoSkillServ;
    
    @GetMapping("/traer")
    @ResponseBody
    public List<Skill> getSkills(){
        return this.skillServ.getSkills();
    }
    @PostMapping("/crear")
    @ResponseBody
    public SkillDto createSkill(@RequestBody SkillDto skillDto){
        Skill savedSkill = new Skill();
        /*
        Atributos no dependiente
        */
        savedSkill.setDescripcion(skillDto.getDescripcion());
        savedSkill.setPorcentaje(skillDto.getPorcentaje());
        /*
        Atributos dependientes
        */
        Perfil perf = this.perfilServ.getPerfilById(skillDto.getIdPerfil());
        savedSkill.setPerfil(perf);
        
        TipoSkill tipo = this.tipoSkillServ.getTipoSkillById(skillDto.getIdTipoSkill());
        savedSkill.setTipoSkill(tipo);
        
        savedSkill = this.skillServ.saveSkill(savedSkill);
        return PasaADto.skill(savedSkill);
    }
    
    
    
    @DeleteMapping("/borrar/{id}")
    public void deleteSkill(@PathVariable Long id){
        this.skillServ.deleteSkillById(id);
    }
    
    @PutMapping("/editar")
    public SkillDto editSkill(@RequestBody SkillDto edittedSkill){
        Skill savedSkill = this.skillServ.getSkillById(edittedSkill.getId());
        savedSkill.setDescripcion(edittedSkill.getDescripcion());
        savedSkill.setPorcentaje(edittedSkill.getPorcentaje());
        this.skillServ.saveSkill(savedSkill);
        return PasaADto.skill(savedSkill);
    }
}
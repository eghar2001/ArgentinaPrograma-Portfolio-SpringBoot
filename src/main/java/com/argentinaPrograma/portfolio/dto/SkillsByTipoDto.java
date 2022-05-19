/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.dto;

import com.argentinaPrograma.portfolio.model.TipoSkill;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author nahux
 */
@Getter @Setter
public class SkillsByTipoDto {
    
    private TipoSkill tipoSkill;
    
    private List<SkillDto> skills;
}

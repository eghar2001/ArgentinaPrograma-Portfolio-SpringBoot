/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.argentinaPrograma.portfolio.service;

import com.argentinaPrograma.portfolio.model.RedSocial;
import java.util.List;

/**
 *
 * @author nahux
 */
public interface IRedSocialService {
    public List<RedSocial> getRedesSociales();
    
    public RedSocial getRedSocialById(Long id);
    
    public void saveRedSocial(RedSocial redSocial);
    
    public void deleteRedSocialById(Long id);
    
    
}

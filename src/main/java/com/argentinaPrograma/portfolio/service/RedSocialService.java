/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.service;

import com.argentinaPrograma.portfolio.model.RedSocial;
import com.argentinaPrograma.portfolio.repository.RedSocialRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author nahux
 */
@Service
@Transactional
public class RedSocialService implements IRedSocialService {
    @Autowired
    private RedSocialRepository redSocialRepo;
    
    @Override
    public List<RedSocial> getRedesSociales(){
        return this.redSocialRepo.findAll();
    }
    
    @Override
    public void saveRedSocial(RedSocial redSocial){
        this.redSocialRepo.save(redSocial);
    }
    
    @Override
    public void deleteRedSocialById(Long id){
        this.redSocialRepo.deleteById(id);
    }
    
    @Override
    public RedSocial getRedSocialById(Long id){
        return this.redSocialRepo.findById(id).orElse(null);
    }
    
    @Override
     public List<RedSocial> getRedesFaltantes(Long idPerfil){
         return this.redSocialRepo.getRedesFaltantes(idPerfil);
     }
}

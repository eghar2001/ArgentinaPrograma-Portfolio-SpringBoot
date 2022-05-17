/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.service;

import com.argentinaPrograma.portfolio.model.Perfil;
import com.argentinaPrograma.portfolio.model.Perfil_has_RedSocial;
import com.argentinaPrograma.portfolio.repository.PerfilHasRedSocialRepository;
import com.argentinaPrograma.portfolio.repository.PerfilRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author nahux
 */
@Transactional
@Service
public class PerfilService implements IPerfilService {
    @Autowired
    private PerfilRepository perfilRepo;
    
    @Autowired
    private PerfilHasRedSocialRepository perfil_redSocial_repo;
    
    @Override
    public List<Perfil> getPerfiles(){
        return this.perfilRepo.findAll();
    }
    
    @Override
    public Perfil getPerfilById(Long id){
        return this.perfilRepo.findById(id).orElse(null);
    }
    
    @Override
    public Perfil savePerfil(Perfil perfilNuevo){
        
        return this.perfilRepo.save(perfilNuevo);
    }
    
    @Override
    public void deletePérfilById(Long id){
    this.perfilRepo.deleteById(id);
    }
    
    @Override
    public void agregaRedSocial(Perfil_has_RedSocial redSocial){
        this.perfil_redSocial_repo.save(redSocial);
    }
}

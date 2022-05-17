/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.argentinaPrograma.portfolio.service;

import com.argentinaPrograma.portfolio.model.Perfil;
import com.argentinaPrograma.portfolio.model.Perfil_has_RedSocial;
import java.util.List;

/**
 *
 * @author nahux
 */
public interface IPerfilService {
    public List<Perfil> getPerfiles();
    
    public Perfil getPerfilById(Long id);
    
    public Perfil savePerfil(Perfil perfilNuevo);
    
    public void deletePérfilById(Long id);
    
    public void agregaRedSocial(Perfil_has_RedSocial redSocial);
}

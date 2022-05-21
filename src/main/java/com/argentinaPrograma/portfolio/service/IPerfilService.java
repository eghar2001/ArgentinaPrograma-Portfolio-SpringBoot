/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.argentinaPrograma.portfolio.service;

import com.argentinaPrograma.portfolio.model.Perfil;
import com.argentinaPrograma.portfolio.model.Perfil_has_RedSocial;
import com.argentinaPrograma.portfolio.model.Perfil_has_RedSocial_ID;
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
    
    public Perfil_has_RedSocial savePerfilRed(Perfil_has_RedSocial perfil_red);
    
    public void deletePerfilRed(Perfil_has_RedSocial_ID id);
    
}

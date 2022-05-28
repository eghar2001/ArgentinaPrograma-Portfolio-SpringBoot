/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.argentinaPrograma.portfolio.repository;

import com.argentinaPrograma.portfolio.model.Perfil_has_RedSocial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nahux
 */
@Repository
public interface PerfilHasRedSocialRepository extends JpaRepository<Perfil_has_RedSocial,Long> {
   @Modifying
   @Query( value = "DELETE FROM perfil_has_redsocial p WHERE p.id_perfil_usuario = ?1 AND p.id_red_social = ?2",nativeQuery = true)
   public void deleteRedPerfil(Long idPerf, Long idRed);
   
}

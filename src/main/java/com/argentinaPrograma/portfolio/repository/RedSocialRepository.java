/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.argentinaPrograma.portfolio.repository;


import com.argentinaPrograma.portfolio.model.RedSocial;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

/**
 *
 * @author nahux
 */
@Repository
public interface RedSocialRepository extends JpaRepository<RedSocial,Long>{
   @Query( value = "SELECT * FROM redsocial r WHERE r.id NOT IN (SELECT id_red_social FROM perfil_has_redsocial p WHERE p.id_perfil_usuario = ?1);",nativeQuery = true)
   public List<RedSocial> getRedesFaltantes(Long idPerf);
   
}

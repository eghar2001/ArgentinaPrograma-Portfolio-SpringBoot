/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.argentinaPrograma.portfolio.repository;


import com.argentinaPrograma.portfolio.model.Provincia;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author nahux
 */
@Transactional
@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia,Long>{
   @Query( value = "SELECT * FROM PROVINCIA p WHERE UPPER (p.nombre) = UPPER(?1)",nativeQuery = true)
   public Optional<Provincia> getProvByNombre(String provincia );
}

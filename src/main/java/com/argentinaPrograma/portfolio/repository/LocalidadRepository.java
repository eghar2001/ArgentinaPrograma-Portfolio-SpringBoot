/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.argentinaPrograma.portfolio.repository;

import com.argentinaPrograma.portfolio.model.Localidad;
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
public interface LocalidadRepository extends JpaRepository<Localidad,Long> {
   @Query( value = "SELECT * FROM LOCALIDAD l WHERE UPPER(l.nombre) = UPPER(?1) AND l.id_provincia = ?2",nativeQuery = true)
   public Optional<Localidad> getLocByNombreAndProv(String localidad, Long id_provincia);
}

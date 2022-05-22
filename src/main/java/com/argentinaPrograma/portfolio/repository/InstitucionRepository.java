/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.argentinaPrograma.portfolio.repository;

import com.argentinaPrograma.portfolio.model.Institucion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nahux
 */
@Repository
public interface InstitucionRepository extends JpaRepository<Institucion,Long>{
       @Query( value = "SELECT * FROM INSTITUCION i WHERE UPPER(i.nombre) = UPPER(?1)",nativeQuery = true)
       public Optional<Institucion> getInstitucionByNombre(String nombre);
}
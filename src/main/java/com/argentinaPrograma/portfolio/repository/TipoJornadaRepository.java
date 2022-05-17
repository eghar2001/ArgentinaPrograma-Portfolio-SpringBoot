/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.argentinaPrograma.portfolio.repository;

import com.argentinaPrograma.portfolio.model.TipoJornada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nahux
 */
@Repository
public interface TipoJornadaRepository extends JpaRepository<TipoJornada,Long>{
    
}

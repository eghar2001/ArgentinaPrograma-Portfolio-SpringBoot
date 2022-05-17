/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.argentinaPrograma.portfolio.repository;

import com.argentinaPrograma.portfolio.model.Educacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nahux
 */
@Repository
public interface EducacionRepository extends JpaRepository<Educacion,Long> {
    @Query(value="SELECT * FROM educacion e WHERE e.perfil_id = ?1 AND e.id_tipo_educacion = ?2",nativeQuery=true)
    public List<Educacion> getEducacionByPerfAndTipo(Long id_perf, Long id_tipo);
}

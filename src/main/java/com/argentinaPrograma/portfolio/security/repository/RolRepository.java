/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.argentinaPrograma.portfolio.security.repository;


import com.argentinaPrograma.portfolio.security.entity.Rol;
import com.argentinaPrograma.portfolio.security.enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nahux
 */
@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.security.service;


import com.argentinaPrograma.portfolio.security.entity.Rol;
import com.argentinaPrograma.portfolio.security.enums.RolNombre;
import com.argentinaPrograma.portfolio.security.repository.RolRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author nahux
 */
@Service
@Transactional
public class RolService {
    
    @Autowired
    private RolRepository rolRepo;
    
    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return this.rolRepo.findByRolNombre(rolNombre);
    }
    
    public void saveRol(Rol rol){
        this.rolRepo.save(rol);
    }
}

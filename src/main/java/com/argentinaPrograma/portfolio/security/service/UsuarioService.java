/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.security.service;

import com.argentinaPrograma.portfolio.security.entity.Usuario;
import com.argentinaPrograma.portfolio.security.repository.UsuarioRepository;
import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author nahux
 */
@Service
@Transactional
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepo;
    
    public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
        return this.usuarioRepo.findByNombreUsuario(nombreUsuario);
    }
    public Usuario getById(Long id){
        return this.usuarioRepo.findById(id).orElse(null);
    }
    
    
    
    public boolean existsByNombreUsuario(String nombreUsuario){
        return this.usuarioRepo.existsByNombreUsuario(nombreUsuario);
    } 
    
    public boolean existsByEmail(String email){
        return this.usuarioRepo.existsByEmail(email);
    }
    
    public void save(Usuario usuario){
        this.usuarioRepo.save(usuario);
    }
    /*
    Utilizados para el mantenimento de usuarios
    */
    @PreAuthorize("hasRole('ADMIN')")
    public List<Usuario> getUsuarios(){
        return this.usuarioRepo.findAll();
    }
    public void deleteUsuarioByUsername(String username){
       this.usuarioRepo.deleteByNombreUsuario(username);
    }
}

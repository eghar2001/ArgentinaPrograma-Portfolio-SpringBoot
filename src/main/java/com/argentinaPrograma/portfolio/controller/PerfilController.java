/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.controller;


import com.argentinaPrograma.portfolio.dto.PerfilDto;
import com.argentinaPrograma.portfolio.dto.RedSocialDto;
import com.argentinaPrograma.portfolio.model.Localidad;

import com.argentinaPrograma.portfolio.model.Perfil;
import com.argentinaPrograma.portfolio.model.Perfil_has_RedSocial;
import com.argentinaPrograma.portfolio.model.Perfil_has_RedSocial_ID;
import com.argentinaPrograma.portfolio.model.Provincia;
import com.argentinaPrograma.portfolio.service.Fecha;
import com.argentinaPrograma.portfolio.service.ILocalidadService;

import com.argentinaPrograma.portfolio.service.IPerfilService;
import com.argentinaPrograma.portfolio.service.IProvinciaService;
import com.argentinaPrograma.portfolio.service.PasaADto;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author nahux
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/perfil")
public class PerfilController {
    @Autowired
    private IPerfilService perfilServ;
    
    
    @Autowired
    private ILocalidadService locServ;
    
    @Autowired
    private IProvinciaService provServ;
    

    
    
    @GetMapping("/traer")
    @ResponseBody
    public List<Perfil> getPerfiles(){
        List<Perfil> perfiles = new ArrayList<>();
        for(Perfil perf: this.perfilServ.getPerfiles()){
            perfiles.add(perf);
        }
        return perfiles;
        
        
    }
    
    @GetMapping("/traer/{id}")
    @ResponseBody
    public Perfil getPerfilById(@PathVariable Long id){
        return this.perfilServ.getPerfilById(id);
    }
    
    @GetMapping("/traer/redesSociales/{id_perfil}")
    @ResponseBody
    public List<RedSocialDto> getRedesSociales(@PathVariable Long id_perfil){
        Perfil perfil = this.perfilServ.getPerfilById(id_perfil);
        List<RedSocialDto> redes = new ArrayList<>(); 
        for(Perfil_has_RedSocial redDePerfil:perfil.getRedesSociales()){
            String nombreRedSocial = redDePerfil.getRedSocial().getNombre();
            String url = redDePerfil.getRedSocialUrl();
            String claseBoxIcon = redDePerfil.getRedSocial().getClaseBoxIcon();
            RedSocialDto red = new RedSocialDto(nombreRedSocial,url,claseBoxIcon);
            redes.add(red);
        }
        return redes;
        
        
    }
    
    @PostMapping("/crear")
    public void createPerfil(@RequestBody Perfil perfilNuevo){
       
       
        
        this.perfilServ.savePerfil(perfilNuevo);
    }
    
    @DeleteMapping("/borrar/{id_perfil}")
    public void deletePerfil(@PathVariable Long id_perfil){
        this.perfilServ.deletePérfilById(id_perfil);
    }
    
    @PutMapping("/editar/{id_perfil}")
    public PerfilDto editPerfil(@PathVariable Long id_perfil,@RequestBody PerfilDto edittedPerfil){
        Perfil savedPerfil = this.getPerfilById(id_perfil);
        savedPerfil.setNombre(edittedPerfil.getNombre());
        savedPerfil.setApellido(edittedPerfil.getApellido());
        savedPerfil.setFechaNac(edittedPerfil.getFechaNac());
        savedPerfil.setProfesion(edittedPerfil.getProfesion());
        savedPerfil.setPerfilUrl(edittedPerfil.getPerfilUrl());
        savedPerfil.setBannerUrl(edittedPerfil.getBannerUrl());
        Provincia prov = this.provServ.provPorNombre(edittedPerfil.getProvincia());
            if(prov == null){
               /*
                Si no existe la provincia que ingresamos, la creamos.
                */
               prov = new Provincia();
               prov.setNombre(edittedPerfil.getProvincia());              
               this.provServ.saveProvincia(prov);
               Localidad locNueva = new Localidad();
              
               locNueva.setNombre(edittedPerfil.getLocalidad());
               locNueva.setProvincia(prov);
               this.locServ.saveLocalidad(locNueva);
               savedPerfil.setLocalidad(locNueva);
            }
           else{             
               /*
                Si existe la provincia, hay que buscar la localidad para esa provincia.
                (Pueden existir localidades que tengan los mismos nombres en 2 provincias distintas. EJ: Pilar en Santa Fe y Buenos Aires)
                */
               Localidad localidad =this.locServ.getLocByNombreAndProv(edittedPerfil.getLocalidad(), prov.getId());
             
               
               if(localidad == null){
                   /*
                   Si no existe, la creamos y la guardamos
                   */
                   Localidad locNueva = new Localidad();
                   
                   locNueva.setNombre(edittedPerfil.getLocalidad());
                   locNueva.setProvincia(prov);
                   this.locServ.saveLocalidad(locNueva);
                   savedPerfil.setLocalidad(locNueva);
               }
               else{
                   /*
                   SI existe, la retornamos y no guardamos nada.
                   Se puede tomar como un metodo de busqueda de localidades secundario, en caso que no sepas el Cod Postal
                   */
                  savedPerfil.setLocalidad(localidad);
                }
           }
        this.perfilServ.savePerfil(savedPerfil);
        PerfilDto returnPerf = PasaADto.perfil(savedPerfil);
        int edad = Fecha.calculaEdad(edittedPerfil.getFechaNac());
        returnPerf.setEdad(edad);
        return returnPerf;
    }
    
    
    
    
    @PutMapping("/{id_perfil}/agregaRedSocial")
    public void agregaRedSocial(@PathVariable Long id_perfil, @RequestBody Perfil_has_RedSocial redSocial){
        Perfil_has_RedSocial_ID redId = redSocial.getId();
        redId.setIdPerfil(id_perfil);
        redSocial.setId(redId);
        this.perfilServ.agregaRedSocial(redSocial);
    }
}

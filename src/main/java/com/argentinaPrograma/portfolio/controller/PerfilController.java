/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.controller;


import com.argentinaPrograma.portfolio.dto.AboutDto;
import com.argentinaPrograma.portfolio.dto.PerfilDto;
import com.argentinaPrograma.portfolio.dto.RedPerfilDto;

import com.argentinaPrograma.portfolio.model.Localidad;

import com.argentinaPrograma.portfolio.model.Perfil;
import com.argentinaPrograma.portfolio.model.Perfil_has_RedSocial;
import com.argentinaPrograma.portfolio.model.Perfil_has_RedSocial_ID;
import com.argentinaPrograma.portfolio.model.Provincia;

import com.argentinaPrograma.portfolio.service.Fecha;
import com.argentinaPrograma.portfolio.service.ILocalidadService;

import com.argentinaPrograma.portfolio.service.IPerfilService;
import com.argentinaPrograma.portfolio.service.IRedSocialService;
import com.argentinaPrograma.portfolio.service.PasaADto;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@CrossOrigin(origins = "https://miportfolioanguar.web.app/")
@RequestMapping("/perfil")
public class PerfilController {
    @Autowired
    private IPerfilService perfilServ;
    
    
    @Autowired
    private ILocalidadService locServ;

    @Autowired
    private IRedSocialService redServ;
    
   
    

    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/traer")
    @ResponseBody
    public List<Perfil> getPerfiles(){
        List<Perfil> perfiles = new ArrayList<>();
        for(Perfil perf: this.perfilServ.getPerfiles()){
            perfiles.add(perf);
        }
        return perfiles;
        
        
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/traer/{id}")
    @ResponseBody
    public Perfil getPerfilById(@PathVariable Long id){
        return this.perfilServ.getPerfilById(id);
    }
    
  
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crear/{id_user}")
    public void createPerfil(@PathVariable Long id_user,@RequestBody PerfilDto perfDto){
        Perfil perf = dtoAPerfil(perfDto);
        perf.setUsuario_id(id_user);
        perf.setAbout(perfDto.getAbout());
        this.perfilServ.savePerfil(perf);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/borrar/{id_perfil}")
    public void deletePerfil(@PathVariable Long id_perfil){
        this.perfilServ.deletePérfilById(id_perfil);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/editar/{id_usuario}")
    public PerfilDto editPerfil(@PathVariable Long id_usuario,@RequestBody PerfilDto edittedPerfil){
        Perfil savedPerfil = dtoAPerfil(edittedPerfil);
        savedPerfil.setUsuario_id(id_usuario);
        
        savedPerfil = this.perfilServ.savePerfil(savedPerfil);
        PerfilDto returnPerf = PasaADto.perfil(savedPerfil);
        int edad = Fecha.calculaEdad(edittedPerfil.getFechaNac());
        returnPerf.setEdad(edad);
        return returnPerf;
    }
    
    
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id_perfil}/editaRedPerfil")
    public RedPerfilDto editRedSociales(@PathVariable Long id_perfil, @RequestBody RedPerfilDto redDto){
        
       
      
            Perfil_has_RedSocial perfil_red = new Perfil_has_RedSocial();
            
            Perfil_has_RedSocial_ID perfil_red_id = new Perfil_has_RedSocial_ID();
            perfil_red_id.setIdPerfil(id_perfil);
            perfil_red_id.setIdRedSocial(redDto.getRedSocial().getId());
            
            perfil_red.setRedSocialUrl(redDto.getUrl());
            perfil_red.setId(perfil_red_id);
            perfil_red.setRedSocial(redDto.getRedSocial());
   
            
            
            
            perfil_red = this.perfilServ.savePerfilRed(perfil_red);
            
       
        return PasaADto.redPerfil(perfil_red);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{idPerfil}/agregaRedPerfil")
    public RedPerfilDto createRedSocial(@PathVariable Long idPerfil, @RequestBody RedPerfilDto redPerfil){
        /*
        Entrada
        RedPerfiLDto:{
        redSocial:RedSocial={'id':number};
        url:string
        }
        idPerfil com path variabel
        */
        Perfil_has_RedSocial perfil_red = new Perfil_has_RedSocial();
        /*
        Creamos el composite id
        */
        Perfil_has_RedSocial_ID perfil_red_id = new Perfil_has_RedSocial_ID();
        perfil_red_id.setIdPerfil(idPerfil);
        perfil_red_id.setIdRedSocial(redPerfil.getRedSocial().getId());
        /*
        Seteamos el id y la url
        */
        perfil_red.setId(perfil_red_id);
        perfil_red.setRedSocialUrl(redPerfil.getUrl());
        perfil_red.setRedSocial(redPerfil.getRedSocial());
        
        Perfil perf = this.perfilServ.getPerfilById(idPerfil);
        perfil_red.setPerfil(perf);
        
        /*
        Guardamos
        */
        perfil_red = this.perfilServ.savePerfilRed(perfil_red);
        
        return PasaADto.redPerfil(perfil_red);        
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{idPerfil}/borraRedPerfil/{idRed}")
    public void deleteRedSocial(@PathVariable Long idPerfil, @PathVariable Long idRed){
        Perfil_has_RedSocial_ID idRedPerf = new Perfil_has_RedSocial_ID();
        
        idRedPerf.setIdPerfil(idPerfil);
        idRedPerf.setIdRedSocial(idRed);
        this.perfilServ.deletePerfilRed(idRedPerf);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{idPerfil}/editaAbout")
    public void editAbout(@PathVariable Long idPerfil,@RequestBody AboutDto ab){
        Perfil perf = this.perfilServ.getPerfilById(idPerfil);
        perf.setAbout(ab.getAbout());
        this.perfilServ.savePerfil(perf);
    }
    
    
    /*
    Funciones que usan los endpoints
    */
    private Perfil dtoAPerfil(PerfilDto perfDto){
        Perfil perf = new Perfil();
        perf.setNombre(perfDto.getNombre());
        perf.setApellido(perfDto.getApellido());
        perf.setFechaNac(perfDto.getFechaNac());
        perf.setProfesion(perfDto.getProfesion());
        perf.setPerfilUrl(perfDto.getPerfilUrl());
        perf.setBannerUrl(perfDto.getBannerUrl());
        Provincia prov = this.locServ.provPorNombre(perfDto.getProvincia());
            if(prov == null){
               /*
                Si no existe la provincia que ingresamos, la creamos.
                */
               prov = new Provincia();
               prov.setNombre(perfDto.getProvincia());              
               this.locServ.saveProvincia(prov);
               Localidad locNueva = new Localidad();
              
               locNueva.setNombre(perfDto.getLocalidad());
               locNueva.setProvincia(prov);
               this.locServ.saveLocalidad(locNueva);
               perf.setLocalidad(locNueva);
            }
           else{             
               /*
                Si existe la provincia, hay que buscar la localidad para esa provincia.
                (Pueden existir localidades que tengan los mismos nombres en 2 provincias distintas. EJ: Pilar en Santa Fe y Buenos Aires)
                */
               Localidad localidad =this.locServ.getLocByNombreAndProv(perfDto.getLocalidad(), prov.getId());
             
               
               if(localidad == null){
                   /*
                   Si no existe, la creamos y la guardamos
                   */
                   Localidad locNueva = new Localidad();
                   
                   locNueva.setNombre(perfDto.getLocalidad());
                   locNueva.setProvincia(prov);
                   this.locServ.saveLocalidad(locNueva);
                   perf.setLocalidad(locNueva);
               }
               else{
                   /*
                   SI existe, la retornamos y no guardamos nada.
                   Se puede tomar como un metodo de busqueda de localidades secundario, en caso que no sepas el Cod Postal
                   */
                  perf.setLocalidad(localidad);
                }
           }
            return perf;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.controller;

import com.argentinaPrograma.portfolio.dto.LocalidadDto;
import com.argentinaPrograma.portfolio.model.Localidad;
import com.argentinaPrograma.portfolio.model.Provincia;
import com.argentinaPrograma.portfolio.service.ILocalidadService;
import com.argentinaPrograma.portfolio.service.PasaADto;
import com.argentinaPrograma.portfolio.verifiers.Verifiers;
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
@CrossOrigin(origins = {"https://miportfolioanguar.web.app","http://localhost:4200/"})
@RequestMapping("/localidad")
public class LocalidadController {
    @Autowired
    private ILocalidadService localidadServ;

  /*
    Endpoints propios de localidad
    */
    @GetMapping("/traer")
    @ResponseBody
    public List<LocalidadDto> getLocalidades(){
        List<LocalidadDto> localidades = new ArrayList<>();
        for(Localidad localidad: this.localidadServ.getLocalidades()){
            localidades.add(PasaADto.localidad(localidad));
        }
        return localidades;
    }
    
    @GetMapping("traer/{codPostal}")
    @ResponseBody
    public LocalidadDto getLocalidadByCodPostal(@PathVariable Long codPostal){
        Localidad loc = this.localidadServ.getLocalidadByCodPostal(codPostal);
        
        if(loc != null){
            LocalidadDto locDto = PasaADto.localidad(loc);
           
            return locDto;
        }
        else{
            return new LocalidadDto();
        }
        
    }
    @GetMapping("existe/{codPostal}")
    @ResponseBody
    public boolean existeLocalidadByCodPostal(@PathVariable Long codPostal){
        return this.localidadServ.existeLocalidad(codPostal);
        
    }
    
    
    
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crear")
    public LocalidadDto crearLocalidad(@RequestBody LocalidadDto loc){
      
       /*
       Verificamos si existe el pais
       */
       
       
         Provincia prov = this.localidadServ.provPorNombre(loc.getProvincia());
            if(prov == null){
               /*
                Si no existe la provincia que ingresamos, la creamos.
                */
               prov = new Provincia();
               prov.setNombre(loc.getProvincia());              
               this.localidadServ.saveProvincia(prov);
               Localidad localidadNueva = new Localidad();
              
               localidadNueva.setNombre(loc.getLocalidad());
               localidadNueva.setProvincia(prov);
               this.localidadServ.saveLocalidad(localidadNueva);
               return loc;
            }
           else{             
               /*
                Si existe la provincia, hay que buscar la localidad para esa provincia.
                (Pueden existir localidades que tengan los mismos nombres en 2 provincias distintas. EJ: Pilar en Santa Fe y Buenos Aires)
                */
               Localidad localidad =this.localidadServ.getLocByNombreAndProv(loc.getLocalidad(), prov.getId());
             
               
               if(localidad == null){
                   /*
                   Si no existe, la creamos y la guardamos
                   */
                   Localidad localidadNueva = new Localidad();
                   
                   localidadNueva.setNombre(loc.getLocalidad());
                   localidadNueva.setProvincia(prov);
                   this.localidadServ.saveLocalidad(localidadNueva);
                   return PasaADto.localidad(localidad);
                   
                   
               }
               else{
                   /*
                   SI existe, la retornamos y no guardamos nada.
                   Se puede tomar como un metodo de busqueda de localidades secundario, en caso que no sepas el Cod Postal
                   */
                   return PasaADto.localidad(localidad);
                }
           }    
          
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/editar/{codPostal}")
    public void editLocalidad(@PathVariable Long codPostal, @RequestBody Localidad edittedLocalidad){
        Localidad returnLocalidad = this.localidadServ.getLocalidadByCodPostal(codPostal);
        String edittedNombre = edittedLocalidad.getNombre();
        if(Verifiers.stringNoVacia(edittedNombre)){
            returnLocalidad.setNombre(edittedNombre);
        }
        Provincia edittedProvin = this.localidadServ.getProvinciaById(edittedLocalidad.getProvincia().getId());
        returnLocalidad.setProvincia(edittedProvin);
        this.localidadServ.saveLocalidad(returnLocalidad);
    }
    
    @DeleteMapping("/borrar/byProv/{id_provincia}")
    public void deleteLocalidadByProv(@PathVariable Long id_provincia){
        this.localidadServ.deleteLocalidad(id_provincia);
    }
    
    @DeleteMapping("/borrar/{id}")
    public void deleteLocalidad(@PathVariable Long id){
        this.localidadServ.deleteLocalidad(id);
    }
    
    /*
    Endpoints de provincia
    */
    
    
    @GetMapping("/provincia/traer")
    @ResponseBody
    public List<Provincia> getProvins(){
        return this.localidadServ.getProvincias();
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/provincia/crear")
    public void createProvin(@RequestBody Provincia provin){    
        this.localidadServ.saveProvincia(provin);
    }

   
}

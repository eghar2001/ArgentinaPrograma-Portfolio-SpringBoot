/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.controller;

import com.argentinaPrograma.portfolio.model.TipoJornada;
import com.argentinaPrograma.portfolio.service.ITipoJornadaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/experiencia/tipoJornada")
public class TipoJornadaController {
    @Autowired
    private ITipoJornadaService tipoJornadaServ;
    
    @GetMapping("/traer")
    @ResponseBody
    public List<TipoJornada> getTiposJornada(){
       return this.tipoJornadaServ.getTiposJornada();
    }
    
    @PostMapping("/crear")
    public void createTipoJornada(@RequestBody TipoJornada tipoJornadaNueva){
        this.tipoJornadaServ.saveTipoJornada(tipoJornadaNueva);
    }
            
}

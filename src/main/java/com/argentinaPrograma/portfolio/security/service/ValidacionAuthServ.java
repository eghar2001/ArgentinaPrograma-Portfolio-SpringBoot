/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaPrograma.portfolio.security.service;

import com.argentinaPrograma.portfolio.security.entity.ValidacionAuth;
import com.argentinaPrograma.portfolio.security.repository.ValidacionAuthRepository;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nahux
 */
@Service
public class ValidacionAuthServ {
    @Autowired
    private ValidacionAuthRepository validRepo;
    
    public ValidacionAuth getValidacion(){
        ValidacionAuth valids = this.validRepo.findById(1).orElse(null);
        if(valids == null){
            valids = new ValidacionAuth();
            //user
            valids.setMaxLengthUser(30);
            valids.setRegexUser(Pattern.compile("^[a-z][.\\w]{4,}$"));
            
            //pass
            valids.setMaxLengthPass(40);
            valids.setRegexPass(Pattern.compile("^[a-zA-Z]{4,}[.\\d]+[\\w.]*$", Pattern.CASE_INSENSITIVE));
            
            //email
            valids.setMaxLengthEmail(60);
            valids.setRegexEmail(Pattern.compile("^[a-z][a-z.\\d]{3,}@(gmail|yahoo|ymail|outlook|hotmail)[.]com([.]ar)?$"));
            
            this.validRepo.save(valids);
        }
        return valids;        
    }
    
    public ValidacionAuth saveValidacion(ValidacionAuth valids){
        return this.validRepo.save(valids);
    }
}

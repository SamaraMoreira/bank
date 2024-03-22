package com.samaravini.bank.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public ResponseEntity<String> integrantes(){
        var integrantes = "Vinicius Monteiro Manfrin (552293) e Samara de Oliveira Moreira(552302)";
        return ResponseEntity.ok(integrantes);
    }

}

package com.example.crudFS.controller;

import com.example.crudFS.model.Personagem;
import com.example.crudFS.service.PersonagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/personagens")
public class PersonagemController {

    @Autowired
    private PersonagemService personagemService;

    @PostMapping
    public ResponseEntity<Personagem> criar(@RequestBody Personagem personagem) {
        try {
            Personagem novoPersonagem = personagemService.criarPersonagem(personagem);
            return ResponseEntity.ok(novoPersonagem);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
package com.example.crudFS.service;

import com.example.crudFS.model.Personagem;
import com.example.crudFS.repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepository personagemRepository;

    public Personagem criarPersonagem(Personagem personagem) {
        if (personagem.getForca() + personagem.getDefesa() > 10) {
            throw new IllegalArgumentException("A soma de Força e Defesa deve ser no máximo 10 pontos.");
        }
        return personagemRepository.save(personagem);
    }
}

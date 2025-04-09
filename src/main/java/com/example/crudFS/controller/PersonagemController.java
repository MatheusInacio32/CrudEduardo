package com.example.crudFS.controller;

import com.example.crudFS.model.ItemMagico;
import com.example.crudFS.model.Personagem;
import com.example.crudFS.service.PersonagemService;
import com.example.crudFS.repository.ItemMagicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personagens")
public class PersonagemController {

    @Autowired
    private PersonagemService personagemService;

    @Autowired
    private ItemMagicoRepository itemMagicoRepository;


    @PostMapping
    public ResponseEntity<Personagem> criar(@RequestBody Personagem personagem) {
        try {
            Personagem novoPersonagem = personagemService.criarPersonagem(personagem);
            return ResponseEntity.ok(novoPersonagem);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @GetMapping
    public ResponseEntity<List<Personagem>> listarPersonagens() {
        return ResponseEntity.ok(personagemService.listarTodos());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Personagem> buscarPorId(@PathVariable Long id) {
        Personagem personagem = personagemService.buscarPorId(id);
        return personagem != null ? ResponseEntity.ok(personagem) : ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Personagem> atualizarPersonagem(@PathVariable Long id, @RequestBody Personagem personagemAtualizado) {
        try {
            Personagem personagemExistente = personagemService.buscarPorId(id);
            if (personagemExistente == null) {
                return ResponseEntity.notFound().build();
            }

            personagemExistente.setNome(personagemAtualizado.getNome());
            personagemExistente.setNomeAventureiro(personagemAtualizado.getNomeAventureiro());
            personagemExistente.setClasse(personagemAtualizado.getClasse());
            personagemExistente.setLevel(personagemAtualizado.getLevel());
            personagemExistente.setForca(personagemAtualizado.getForca());
            personagemExistente.setDefesa(personagemAtualizado.getDefesa());


            Personagem atualizado = personagemService.atualizarPersonagem(personagemExistente);
            return ResponseEntity.ok(atualizado);
        } catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPersonagem(@PathVariable Long id) {
        try {
            personagemService.remover(id);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/{id}/itens")
    public ResponseEntity<Personagem> adicionarItem(@PathVariable Long id, @RequestBody ItemMagico item) {
        try {
            Personagem personagem = personagemService.adicionarItem(id, item);
            return ResponseEntity.ok(personagem);
        } catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/{id}/itens")
    public ResponseEntity<List<ItemMagico>> listarItens(@PathVariable Long id) {
        Personagem personagem = personagemService.buscarPorId(id);
        return personagem != null
                ? ResponseEntity.ok(personagem.getItens())
                : ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}/itens/{itemId}")
    public ResponseEntity<Personagem> removerItem(@PathVariable Long id, @PathVariable Long itemId) {
        try {
            Personagem personagem = personagemService.removerItem(id, itemId);
            return ResponseEntity.ok(personagem);
        } catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/{id}/amuleto")
    public ResponseEntity<ItemMagico> buscarAmuleto(@PathVariable Long id) {
        try {
            ItemMagico amuleto = personagemService.buscarAmuleto(id);
            return ResponseEntity.ok(amuleto);
        } catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

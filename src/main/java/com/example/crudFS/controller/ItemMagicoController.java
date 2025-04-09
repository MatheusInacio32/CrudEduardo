package com.example.crudFS.controller;

import com.example.crudFS.model.ItemMagico;
import com.example.crudFS.model.TipoItem;
import com.example.crudFS.repository.ItemMagicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens")
public class ItemMagicoController {

    @Autowired
    private ItemMagicoRepository itemMagicoRepository;


    @GetMapping
    public ResponseEntity<List<ItemMagico>> listarItens() {
        return ResponseEntity.ok(itemMagicoRepository.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<ItemMagico> buscarPorId(@PathVariable Long id) {
        return itemMagicoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<ItemMagico> criarItem(@RequestBody ItemMagico item) {

        if(item.getTipo() == TipoItem.ARMA && item.getDefesa() != 0) {
            return ResponseEntity.badRequest().build();
        }
        if(item.getTipo() == TipoItem.ARMADURA && item.getForca() != 0) {
            return ResponseEntity.badRequest().build();
        }
        if(item.getForca() == 0 && item.getDefesa() == 0) {
            return ResponseEntity.badRequest().build();
        }
        if(item.getForca() > 10 || item.getDefesa() > 10) {
            return ResponseEntity.badRequest().build();
        }
        ItemMagico novoItem = itemMagicoRepository.save(item);
        return ResponseEntity.ok(novoItem);
    }
}


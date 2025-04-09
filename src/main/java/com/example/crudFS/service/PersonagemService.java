package com.example.crudFS.service;

import com.example.crudFS.model.Personagem;
import com.example.crudFS.model.ItemMagico;
import com.example.crudFS.repository.PersonagemRepository;
import com.example.crudFS.repository.ItemMagicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepository personagemRepository;

    @Autowired
    private ItemMagicoRepository itemMagicoRepository;

    public Personagem criarPersonagem(Personagem personagem) {
        if (personagem.getForca() + personagem.getDefesa() > 10) {
            throw new IllegalArgumentException("A soma de Força e Defesa deve ser no máximo 10 pontos.");
        }
        return personagemRepository.save(personagem);
    }

    public List<Personagem> listarTodos() {
        return personagemRepository.findAll();
    }

    public Personagem buscarPorId(Long id) {
        return personagemRepository.findById(id).orElse(null);
    }

    public Personagem atualizarPersonagem(Personagem personagem) {
        if (personagem.getForca() + personagem.getDefesa() > 10) {
            throw new IllegalArgumentException("A soma de Força e Defesa deve ser no máximo 10 pontos.");
        }
        return personagemRepository.save(personagem);
    }

    public void remover(Long id) {
        personagemRepository.deleteById(id);
    }

    public Personagem adicionarItem(Long personagemId, ItemMagico item) {
        Personagem personagem = buscarPorId(personagemId);
        if (personagem == null) {
            throw new RuntimeException("Personagem não encontrado.");
        }
        personagem.adicionarItem(item);
        return personagemRepository.save(personagem);
    }

    public Personagem removerItem(Long personagemId, Long itemId) {
        Personagem personagem = buscarPorId(personagemId);
        if (personagem == null) {
            throw new RuntimeException("Personagem não encontrado.");
        }
        ItemMagico item = personagem.getItens().stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Item não encontrado para esse personagem."));
        personagem.getItens().remove(item);
        itemMagicoRepository.delete(item);
        return personagemRepository.save(personagem);
    }

    public ItemMagico buscarAmuleto(Long personagemId) {
        Personagem personagem = buscarPorId(personagemId);
        if (personagem == null) {
            throw new RuntimeException("Personagem não encontrado.");
        }
        return personagem.getItens().stream()
                .filter(i -> i.getTipo() == com.example.crudFS.model.TipoItem.AMULETO)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Amuleto não encontrado para esse personagem."));
    }
}

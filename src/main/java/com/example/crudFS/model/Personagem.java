package com.example.crudFS.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String nomeAventureiro;

    @Enumerated(EnumType.STRING)
    private Classe classe;

    private int level;
    private int forca;
    private int defesa;

    @JsonManagedReference
    @OneToMany(mappedBy = "personagem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemMagico> itens = new ArrayList<>();


    public Personagem() {
    }

    public Personagem(String nome, String nomeAventureiro, Classe classe, int level, int forca, int defesa) {
        this.nome = nome;
        this.nomeAventureiro = nomeAventureiro;
        this.classe = classe;
        this.level = level;
        this.forca = forca;
        this.defesa = defesa;
    }

    public void adicionarItem(ItemMagico item) {
        if (item.getTipo() == TipoItem.AMULETO) {
            boolean jaTemAmuleto = this.itens.stream()
                    .anyMatch(i -> i.getTipo() == TipoItem.AMULETO);
            if (jaTemAmuleto) {
                throw new IllegalStateException("Personagem já possui um amuleto.");
            }
        }
        item.setPersonagem(this);
        itens.add(item);
    }


    public Long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNomeAventureiro() {
        return nomeAventureiro;
    }
    public void setNomeAventureiro(String nomeAventureiro) {
        this.nomeAventureiro = nomeAventureiro;
    }
    public Classe getClasse() {
        return classe;
    }
    public void setClasse(Classe classe) {
        this.classe = classe;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getForca() {
        return forca;
    }
    public void setForca(int forca) {
        this.forca = forca;
    }
    public int getDefesa() {
        return defesa;
    }
    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }
    public List<ItemMagico> getItens() {
        return itens;
    }
    public void setItens(List<ItemMagico> itens) {
        this.itens = itens;
    }
}

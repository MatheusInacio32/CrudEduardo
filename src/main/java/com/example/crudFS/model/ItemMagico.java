package com.example.crudFS.model;

import jakarta.persistence.*;

@Entity
public class ItemMagico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoItem tipo;

    private int forca;
    private int defesa;

    @ManyToOne
    @JoinColumn(name = "personagem_id")
    private Personagem personagem;

    public ItemMagico() {
    }

    public ItemMagico(String nome, TipoItem tipo, int forca, int defesa) {
        this.nome = nome;
        this.tipo = tipo;
        this.forca = forca;
        this.defesa = defesa;
    }


    @PrePersist
    @PreUpdate
    private void validarAtributos() {
        if (tipo == null) return;
        switch (tipo) {
            case ARMA -> this.defesa = 0;
            case ARMADURA -> this.forca = 0;
            case AMULETO -> {

            }
        }
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
    public TipoItem getTipo() {
        return tipo;
    }
    public void setTipo(TipoItem tipo) {
        this.tipo = tipo;
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
    public Personagem getPersonagem() {
        return personagem;
    }
    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }
}


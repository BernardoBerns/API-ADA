package com.study.dto;

public class CursoDtoResponse {
    private String nome;
    private String descricao;
    private int duracao;

    public CursoDtoResponse() {
    }

    public CursoDtoResponse(String nome, String descricao, int duracao) {
        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
}

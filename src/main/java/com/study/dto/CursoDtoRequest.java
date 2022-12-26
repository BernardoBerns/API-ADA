package com.study.dto;

public class CursoDtoRequest{
    private String nome;
    private String descricao;
    private Integer duracao;
    
    public CursoDtoRequest() {
    }

    public CursoDtoRequest(String nome, String descricao, Integer duracao) {
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

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    
}
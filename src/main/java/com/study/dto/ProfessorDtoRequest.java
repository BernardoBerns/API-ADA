package com.study.dto;

public class ProfessorDtoRequest {
    private String nome;
    private String titulo;

    public ProfessorDtoRequest() {
    }

    public ProfessorDtoRequest(String nome, String titulo) {
        this.nome = nome;
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

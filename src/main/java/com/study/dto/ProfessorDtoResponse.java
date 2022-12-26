package com.study.dto;

import com.study.enums.SEXO;

public class ProfessorDtoResponse {
    private String titulo;
    private String nome;
    private SEXO sexo;

    public ProfessorDtoResponse() {
    }

    public ProfessorDtoResponse(String titulo, String nome, SEXO sexo) {
        this.titulo = titulo;
        this.nome = nome;
        this.sexo = sexo;
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

    public SEXO getSexo() {
        return sexo;
    }

    public void setSexo(SEXO sexo) {
        this.sexo = sexo;
    }
}

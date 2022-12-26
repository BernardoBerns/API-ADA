package com.study.dto;

import com.study.enums.SEXO;

public class AlunoDtoResponse {
    private String matricula;
    private String nome;
    private SEXO sexo;

    public AlunoDtoResponse() {
    }

    public AlunoDtoResponse(String matricula, String nome, SEXO sexo) {
        this.matricula = matricula;
        this.nome = nome;
        this.sexo = sexo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
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

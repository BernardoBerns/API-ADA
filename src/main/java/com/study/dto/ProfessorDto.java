package com.study.dto;

import com.study.enums.SEXO;

public class ProfessorDto {
    private Integer id;
    private String nome;
    private String titulo;
    private SEXO sexo;
    
    
    public ProfessorDto(Integer id, String nome, String titulo, SEXO sexo) {
        this.id = id;
        this.nome = nome;
        this.titulo = titulo;
        this.sexo = sexo;
    }
    public ProfessorDto() {
    }
    @Override
    public String toString() {
        return "ProfessorDto [id=" + id + ", nome=" + nome + ", titulo=" + titulo + ", sexo=" + sexo + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
        result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
        return false;
        if (getClass() != obj.getClass())
        return false;
        ProfessorDto other = (ProfessorDto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
            } else if (!id.equals(other.id))
            return false;
            if (nome == null) {
                if (other.nome != null)
                return false;
            } else if (!nome.equals(other.nome))
            return false;
            if (titulo == null) {
                if (other.titulo != null)
                return false;
            } else if (!titulo.equals(other.titulo))
            return false;
            if (sexo != other.sexo)
            return false;
            return true;
        }
        
        public Integer getId() {
            return id;
        }
        
        public void setId(Integer id) {
            this.id = id;
        }
        public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public SEXO getSexo() {
        return sexo;
    }
    public void setSexo(SEXO sexo) {
        this.sexo = sexo;
    }

}
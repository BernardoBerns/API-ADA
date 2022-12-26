package com.study.dao;

import java.util.List;
import java.util.Objects;

import javax.enterprise.context.RequestScoped;

import com.study.model.Curso;

@RequestScoped
public class CursoDao {

    public Curso addCurso(Curso curso) {
        curso.persistAndFlush();
        return curso;
    }

    public List<Curso> getCursos() {
        return Curso.listAll();
    }

    public Curso getCursoById(Integer id) {
        return Curso.findById(id);
    }

    public List<Curso> getCursoByName(String nome) {
        return Curso.find("Nome LIKE ?1", "%" + nome + "%").list();
    }

    public Curso alterarCurso(Integer id, Curso curso) {
        Curso cursoExistente = getCursoById(id);
        if (Objects.isNull(cursoExistente)) {
            return new Curso();
        }
        cursoExistente.setNome(curso.getNome());
        cursoExistente.setDescricao(curso.getDescricao());
        cursoExistente.setDuracao(curso.getDuracao());
        cursoExistente.persistAndFlush();
        return cursoExistente;
    }

    public Curso excluirCurso(Integer id) {
        Curso curso = getCursoById(id);
        curso.delete();
        return curso;
    }
}

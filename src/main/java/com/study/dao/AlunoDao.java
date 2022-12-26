package com.study.dao;

import java.util.List;
import java.util.Objects;

import javax.enterprise.context.RequestScoped;

import com.study.model.Aluno;

@RequestScoped
public class AlunoDao {

    public Aluno addAluno(Aluno aluno) {
        aluno.persistAndFlush();
        return aluno;
    }

    public List<Aluno> getAlunos() {
        return Aluno.listAll();
    }

    public Aluno getAlunoById(Integer id) {
        return Aluno.findById(id);
    }

    public List<Aluno> getAlunoByName(String nome) {
        return Aluno.find("Nome LIKE ?1", "%" + nome + "%").list();
    }

    public Aluno alterarAluno(Integer id, Aluno aluno) {
        Aluno alunoExistente = getAlunoById(id);
        if (Objects.isNull(alunoExistente)) {
            return new Aluno();
        }
        alunoExistente.setNome(aluno.getNome());
        alunoExistente.setMatricula(aluno.getMatricula());
        alunoExistente.setSexo(aluno.getSexo());
        alunoExistente.persistAndFlush();
        return alunoExistente;
    }

    public Aluno excluirAluno(Integer id) {
        Aluno aluno = getAlunoById(id);
        aluno.delete();
        return aluno;
    }
}

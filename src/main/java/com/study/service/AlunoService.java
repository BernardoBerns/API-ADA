package com.study.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.study.dao.AlunoDao;
import com.study.dto.AlunoDtoRequest;
import com.study.dto.AlunoDtoResponse;
import com.study.mapper.AlunoMapper;

@RequestScoped
public class AlunoService {
    @Inject
    private AlunoDao alunoDao;

    @Inject
    private AlunoMapper alunoMapper;

    @Transactional
    public AlunoDtoResponse cadastrar(AlunoDtoRequest aluno) {
        return alunoMapper.toResponse(alunoDao.addAluno(alunoMapper.toEntity(aluno)));
    }

    @Transactional
    public List<AlunoDtoResponse> ListarTodos() {
        return alunoMapper.toListResponse(alunoDao.getAlunos());
    }

    @Transactional
    public AlunoDtoResponse BuscarPorId(Integer id) {
        return alunoMapper.toResponse(alunoDao.getAlunoById(id));
    }

    @Transactional
    public List<AlunoDtoResponse> BurcarPorNome(String nome) {
        return alunoMapper.toListResponse(alunoDao.getAlunoByName(nome));
    }

    @Transactional
    public AlunoDtoResponse alterar(Integer id, AlunoDtoRequest aluno) {
        return alunoMapper.toResponse(alunoDao.alterarAluno(id, alunoMapper.toEntity(aluno)));
    }

    @Transactional
    public AlunoDtoResponse excluir(Integer id) {
        return alunoMapper.toResponse(alunoDao.excluirAluno(id));
    }
}

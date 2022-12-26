package com.study.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.study.dao.CursoDao;
import com.study.dto.CursoDtoRequest;
import com.study.dto.CursoDtoResponse;
import com.study.mapper.CursoMapper;
import com.study.model.Curso;

@RequestScoped
public class CursoService {
    @Inject
    private CursoDao cursoDao;

    @Inject
    private CursoMapper cursoMapper;

    @Transactional
    public CursoDtoResponse cadastrar(CursoDtoRequest curso) {
        Curso curso1 = cursoMapper.toEntity(curso);
        Curso curso2 = cursoDao.addCurso(curso1);
        CursoDtoResponse curso3 = cursoMapper.toResponse(curso2);
        // return cursoMapper.toResponse(cursoDao.addCurso(cursoMapper.toEntity(curso)));
        return curso3;
    }

    @Transactional
    public List<CursoDtoResponse> ListarTodos() {
        return cursoMapper.toListResponse(cursoDao.getCursos());
    }

    @Transactional
    public CursoDtoResponse BuscarPorId(Integer id) {
        return cursoMapper.toResponse(cursoDao.getCursoById(id));
    }

    @Transactional
    public List<CursoDtoResponse> BurcarPorNome(String nome) {
        return cursoMapper.toListResponse(cursoDao.getCursoByName(nome));
    }

    @Transactional
    public CursoDtoResponse alterar(Integer id, CursoDtoRequest curso) {
        return cursoMapper.toResponse(cursoDao.alterarCurso(id, cursoMapper.toEntity(curso)));
    }

    @Transactional
    public CursoDtoResponse excluir(Integer id) {
        return cursoMapper.toResponse(cursoDao.excluirCurso(id));
    }
}

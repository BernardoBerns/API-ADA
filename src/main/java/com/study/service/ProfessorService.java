package com.study.service;

import java.util.List;
import java.util.Objects;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.study.dto.ProfessorRequest;
import com.study.dto.ProfessorResponse;
import com.study.mapper.ProfessorMapper;
import com.study.model.Professor;
import com.study.repository.ProfessorRepository;

@RequestScoped
public class ProfessorService {

    @Inject
    private ProfessorRepository repository;

    @Inject
    private ProfessorMapper mapper;

    @Transactional
    public ProfessorResponse cadastrarProfessor(@Valid ProfessorRequest professor) {
        Objects.requireNonNull(professor, "O campo professor deve ser preenchido");
        Professor entityProfessor = mapper.toEntity(professor);
        repository.persistAndFlush(entityProfessor);
        return mapper.toResponse(entityProfessor);
    }

    @Transactional
    public List<ProfessorResponse> listarTodos() {
        return mapper.toResponse(repository.listAll());
    }

    @Transactional
    public ProfessorResponse buscarPeloId(int id) {

        Professor entityProfessor = repository.findById(id);
        if (Objects.isNull(entityProfessor))
            throw new EntityNotFoundException("Professor não encontrado");

        return mapper.toResponse(entityProfessor);
    }

    @Transactional
    public List<ProfessorResponse> burcarPeloNome(String nome) {
        return mapper.toResponse(repository.find("Nome LIKE ?1", "%" + nome + "%").list());
    }

    @Transactional
    public ProfessorResponse atualizar(Integer id, @Valid ProfessorRequest professor) {
        Objects.requireNonNull(professor, "O campo professor deve ser preenchido");
        Professor professorEntity = repository.findById(id);
        professorEntity.setNome(professor.getNome());
        professorEntity.setDisciplina(null);
        return mapper.toResponse(professorEntity);
    }

    @Transactional
    public Boolean deletar(Integer id) {
        return repository.deleteById(id);
    }
}
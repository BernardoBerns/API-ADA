package com.study.service;

import com.study.dto.DisciplinaRequest;
import com.study.dto.DisciplinaResponse;
import com.study.dto.TitularResponse;
import com.study.exceptions.InvalidStateException;
import com.study.mapper.DisciplinaMapper;
import com.study.model.Disciplina;
import com.study.model.Professor;
import com.study.repository.DisciplinaRepository;
import com.study.repository.ProfessorRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
@Slf4j
@RequiredArgsConstructor
public class DisciplinaService {

    @Inject
    private DisciplinaMapper mapper;

    @Inject
    private DisciplinaRepository repository;

    @Inject
    private ProfessorRepository professorRepository;

    public List<DisciplinaResponse> listarTodos() {
        return mapper.toResponse(repository.listAll());
    }

    @Transactional
    public DisciplinaResponse cadastrarDisciplina(@Valid DisciplinaRequest disciplina) {
        Objects.requireNonNull(disciplina, "O campo disciplina deve ser preenchido");
        Disciplina entityDisciplina = mapper.toEntity(disciplina);
        repository.persistAndFlush(entityDisciplina);
        return mapper.toResponse(entityDisciplina);
    }

    @Transactional
    public DisciplinaResponse buscarPeloId(int id) {

        Disciplina entityDisciplina = repository.findById(id);
        if (Objects.isNull(entityDisciplina))
            throw new EntityNotFoundException("Disciplina não encontrado");

        return mapper.toResponse(entityDisciplina);
    }

    @Transactional
    public List<DisciplinaResponse> buscarPeloNome(String nome){
        return mapper.toResponse(repository.find("Nome LIKE ?1", "%" + nome + "%").list());
    }

    @Transactional
    public DisciplinaResponse atualizar(Integer id,@Valid DisciplinaRequest disciplina) {
        Objects.requireNonNull(disciplina, "O campo disciplina deve ser preenchido");
        Disciplina disciplinaEntity = repository.findById(id);
        disciplinaEntity.setNome(disciplina.getNome());
        disciplinaEntity.setTitular(null);
        return mapper.toResponse(disciplinaEntity);
    }

    @Transactional
    public Boolean deletar(Integer id) {
        return repository.deleteById(id);
    }

    @Transactional
    public TitularResponse atualizarTitular(int idDisciplina, int idProfessor) {

        var disciplina = repository.findById(idDisciplina);
        var professor = professorRepository.findById(idProfessor);

        if (Objects.isNull(disciplina)) throw new EntityNotFoundException("Disciplina não encontrada");
        if (Objects.isNull(professor)) throw new EntityNotFoundException("Professor noão encontrado");

        if (repository.countTitularidadeByProfessor(professor) > 0) {
            throw new InvalidStateException("Professor não deve ser titular de nenhuma outra disciplina");
        }

        disciplina.setTitular(professor);
        repository.persist(disciplina);

        return mapper.toResponse(professor);
    }

    public DisciplinaResponse getDisciplinaByProfessorId(int idProfessor) {

        Professor professor = professorRepository.findById(idProfessor);
        if (Objects.isNull(professor)) throw new EntityNotFoundException("Professor not found");

        PanacheQuery<Disciplina> query = repository.find("titular", professor);
        if (query.count() == 0) throw new EntityNotFoundException("Disciplina não encontrada");
        if (query.count() > 1) throw new InvalidStateException("Professor deve ser titular de no máximo uma disciplina");

        Disciplina disciplina = query.singleResult();

        return mapper.toResponse(disciplina);

    }
}

package com.study.service;

import com.study.dto.AlunoRequest;
import com.study.dto.AlunoResponse;
import com.study.dto.TutorResponse;
import com.study.mapper.AlunoMapper;
import com.study.model.Aluno;
import com.study.repository.AlunoRepository;
import com.study.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
@RequiredArgsConstructor
public class AlunoService {

    @Inject
    private AlunoMapper mapper;

    @Inject
    private AlunoRepository repository;

    @Inject
    private ProfessorRepository professorRepository;

    public List<AlunoResponse> listarTodos() {
        return mapper.toResponse(repository.listAll());
    }

    @Transactional
    public AlunoResponse cadastrarAluno(@Valid AlunoRequest aluno) {
        Objects.requireNonNull(aluno, "O campo aluno deve ser preenchido");
        Aluno entityAluno = mapper.toEntity(aluno);
        repository.persistAndFlush(entityAluno);
        return mapper.toResponse(entityAluno);
    }

    @Transactional
    public AlunoResponse buscarPeloId(int id) {

        Aluno entityAluno = repository.findById(id);
        if (Objects.isNull(entityAluno))
            throw new EntityNotFoundException("Aluno não encontrado");

        return mapper.toResponse(entityAluno);
    }

    @Transactional
    public List<AlunoResponse> buscarPeloNome(String nome){
        return mapper.toResponse(repository.find("Nome LIKE ?1", "%" + nome + "%").list());
    }

    @Transactional
    public AlunoResponse atualizar(Integer id,@Valid AlunoRequest aluno) {
        Objects.requireNonNull(aluno, "O campo aluno deve ser preenchido");
        Aluno alunoEntity = repository.findById(id);
        alunoEntity.setNome(aluno.getNome());
        alunoEntity.setTutor(null);
        return mapper.toResponse(alunoEntity);
    }

    @Transactional
    public Boolean deletar(Integer id) {
        return repository.deleteById(id);
    }

    @Transactional
    public TutorResponse atualizarTutor(int idAluno, int idProfessor) {

        var aluno = repository.findById(idAluno);
        var professor = professorRepository.findById(idProfessor);

        if (Objects.isNull(aluno))
            throw new EntityNotFoundException("Aluno not found");
        if (Objects.isNull(professor))
            throw new EntityNotFoundException("Professor not found");

        aluno.setTutor(professor);
        repository.persist(aluno);

        return mapper.toResponse(professor);
    }

    public List<AlunoResponse> getTutoradosByProfessorId(int idProfessor) {

        var professor = professorRepository.findById(idProfessor);
        if (Objects.isNull(professor))
            throw new EntityNotFoundException("Professor not found");

        List<Aluno> listOfEntities = repository.getTutoradosByProfessor(professor);

        return mapper.toResponse(listOfEntities);
    }
}

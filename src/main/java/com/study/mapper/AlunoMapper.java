package com.study.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import com.study.dto.AlunoDtoRequest;
import com.study.dto.AlunoDtoResponse;
import com.study.model.Aluno;

@RequestScoped
public class AlunoMapper {
    public AlunoDtoResponse toResponse(Aluno aluno) {
        return new AlunoDtoResponse(aluno.getMatricula(), aluno.getNome(), aluno.getSexo());
    }

    public List<AlunoDtoResponse> toListResponse(List<Aluno> alunos) {
        List<AlunoDtoResponse> alunosResponse = new ArrayList<AlunoDtoResponse>();
        alunos.stream().forEach(a -> alunosResponse.add(toResponse(a)));
        return alunosResponse;
    }

    public Aluno toEntity(AlunoDtoRequest alunoRequest) {
        return new Aluno(null, alunoRequest.getNome(), alunoRequest.getMatricula(), alunoRequest.getSexo());
    }
}

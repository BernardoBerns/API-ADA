package com.study.mapper;

import java.util.ArrayList;
import java.util.List;

import com.study.dto.ProfessorDtoRequest;
import com.study.dto.ProfessorDtoResponse;
import com.study.model.Professor;

public class ProfessorMapper {
    public ProfessorDtoResponse toResponse(Professor professor) {
        return new ProfessorDtoResponse(professor.getTitulo(), professor.getNome(), professor .getSexo());
    }

    public List<ProfessorDtoResponse> toListResponse(List<Professor> professores) {
        List<ProfessorDtoResponse> professoresResponse = new ArrayList<ProfessorDtoResponse>();
        professores.stream().forEach(a -> professoresResponse.add(toResponse(a)));
        return professoresResponse;
    }

    public Professor toEntity(ProfessorDtoRequest professorResquest) {
        return new Professor(null, professorResquest.getNome(), null, null);
    }
}

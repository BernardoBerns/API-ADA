package com.study.mapper;

import com.study.dto.ProfessorRequest;
import com.study.dto.ProfessorResponse;
import com.study.model.Professor;

import javax.enterprise.context.ApplicationScoped;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProfessorMapper {
    public List<ProfessorResponse> toResponse(List<Professor> listOfEntities) {

        if (Objects.isNull(listOfEntities)) return new ArrayList<>();

        return listOfEntities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ProfessorResponse toResponse(Professor entity) {

        Objects.requireNonNull(entity, "Entity must be not null");

        var formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY hh:mm:ss");
        return  ProfessorResponse.builder()
                    .id(entity.getId())
                    .nome(entity.getNome())
                    .dateTime(formatter.format(entity.getDateTime()))
                    .build();
    }

    public Professor toEntity(ProfessorRequest professorRequest) {
        return new Professor(null, professorRequest.getNome(), null, null);
    }
}
package com.study.mapper;

import com.study.dto.AlunoRequest;
import com.study.dto.AlunoResponse;
import com.study.dto.TutorResponse;
import com.study.model.Aluno;
import com.study.model.Professor;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public class AlunoMapper {

    public List<AlunoResponse> toResponse(List<Aluno> listOfEntities) {

        if (Objects.isNull(listOfEntities)) return new ArrayList<>();

        return listOfEntities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

    }

    public AlunoResponse toResponse(Aluno entity) {

        Objects.requireNonNull(entity, "entity must not be null");

        var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");

        var response =
                AlunoResponse.builder()
                    .id(entity.getId())
                    .name(entity.getNome())
                    .dateTime(formatter.format(entity.getDateTime()))
                    .build();

        if (Objects.nonNull(entity.getTutor())) {
            response.setTutor(entity.getTutor().getNome());
        }

        return response;
    }

    public Aluno toEntity(AlunoRequest alunoRequest) {
        return new Aluno(null, alunoRequest.getNome(), null, null);
    }

    public TutorResponse toResponse(Professor entity) {

        Objects.requireNonNull(entity, "entity must not be null");

        var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");

        return TutorResponse.builder()
                .tutor(entity.getNome())
                .atualizacao(formatter.format(LocalDateTime.now()))
                .build();

    }
}

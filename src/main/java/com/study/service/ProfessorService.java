package com.study.service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import com.study.dto.ProfessorDto;

@ApplicationScoped
public class ProfessorService {

    private HashMap<Integer, ProfessorDto> professores = new HashMap<>();

    public void cadastrandoProfessor(ProfessorDto professor){
        professores.put(professor.getId(), professor);
    }

    public ProfessorDto getById(Integer id) {
        return  professores.get(id);
    }

    public List<ProfessorDto> listAll() {
        return professores.values().stream().collect(Collectors.toList());
    }

    public List<ProfessorDto> buscarPorNome(String nome) {
        return professores.values().stream().filter(a -> a.getNome().startsWith(nome)).collect(Collectors.toList());
    }

    public ProfessorDto alterarProfessor(ProfessorDto professor) {
        if(Objects.isNull(professores.get(professor.getId())))
            return null;
        return professores.put(professor.getId(), professor);
    }

    public ProfessorDto deletar(Integer id) {
        ProfessorDto professor = professores.get(id);
        return professores.remove(professor.getId());
    }


}

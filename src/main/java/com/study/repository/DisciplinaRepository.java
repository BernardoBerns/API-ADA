package com.study.repository;

import com.study.model.Disciplina;
import com.study.model.Professor;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DisciplinaRepository  implements PanacheRepositoryBase<Disciplina, Integer> {

    //count how many titularidade
    public long countTitularidadeByProfessor(Professor professor) {

        var query = find("titular", professor);
        return query.count();
    }
}

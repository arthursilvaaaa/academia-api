package com.academia.api.Repository;

import com.academia.api.Model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    boolean existsByAlunoId(Long alunoId);
    boolean existsByAulaId(Long aulaId);
}

package com.academia.api.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "matricula")
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "aula_id", nullable = false)
    private Aula aula;

    @Column(nullable = false)
    private LocalDate dataMatricula;


    void definirAluno(Aluno aluno){
        if (aluno == null){
            throw new IllegalArgumentException
                        ("O aluno não pode ser nulo");
        }
        this.aluno = aluno;
    }
    void definirAula(Aula aula){
        if (aula==null){
            throw new IllegalArgumentException
                        ("A aula não pode ser nula");
        }
        this.aula = aula;
    }
}

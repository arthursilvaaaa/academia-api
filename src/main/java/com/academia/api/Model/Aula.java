package com.academia.api.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "aula")
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDateTime horario;

    @ManyToOne
    @JoinColumn(name = "instrutor_id", nullable = false)
    private Instrutor instrutor;

    @OneToMany(mappedBy = "aula")
    private List<Matricula> matriculas = new ArrayList<>();

    public void adicionarMatricula(Matricula matricula){
        if (matricula==null) {
            throw new IllegalArgumentException("A matricula não pode estar nula");
        }
        if (!matriculas.contains(matricula)) {
            matriculas.add(matricula);
        }
        matricula.definirAula(this);
    }
}

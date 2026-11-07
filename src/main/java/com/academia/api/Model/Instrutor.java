package com.academia.api.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "instrutor")
public class Instrutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String especialidade;

    @OneToMany(mappedBy = "instrutor")
    private List<Aula> aulas = new ArrayList<>();

    public void adicionarAula(Aula aula){
        if (aula == null){
            throw new IllegalArgumentException("A aula não pode estar nula");
        }
        if (getAulas().contains(aula)) {
            throw new IllegalArgumentException
                    ("Não é possível adiconar uma mesma aula para esse instrutor");
        }
        if (aula.getInstrutor() == this){
            throw new IllegalArgumentException
                    ("Não é possível adicionar um mesmo instrutor para essa aula");
        }
        aulas.add(aula);
        aula.setInstrutor(this);
    }
}

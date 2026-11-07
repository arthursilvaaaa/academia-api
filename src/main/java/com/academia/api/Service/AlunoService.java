package com.academia.api.Service;

import com.academia.api.DTOS.AlunoRequestDTO;
import com.academia.api.DTOS.AlunoResponseDTO;
import com.academia.api.Model.Aluno;
import com.academia.api.Model.Matricula;
import com.academia.api.Repository.AlunoRepository;
import com.academia.api.Repository.MatriculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {
    private final AlunoRepository alunoRepository;
    private final MatriculaRepository matriculaRepository;

    @Transactional(readOnly = true)
    public List<AlunoResponseDTO> listAll(){
        return alunoRepository.findAll().stream().map(AlunoResponseDTO::fromEntity).toList();
    }

    @Transactional(readOnly = true)
    public AlunoResponseDTO findById(Long id){
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Aluno não encontrado"));
        return AlunoResponseDTO.fromEntity(aluno);
    }

    @Transactional(readOnly = false)
    public AlunoResponseDTO save (AlunoRequestDTO dto){
        Aluno aluno = new Aluno();
        aluno.setNome(dto.nome());
        aluno.setDataNascimento(dto.dataNascimento());
        aluno.setEmail(dto.email());
        alunoRepository.save(aluno);

        return AlunoResponseDTO.fromEntity(aluno);
    }

    @Transactional
    public void delete(Long id) {
        if (!alunoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado");
        }
        if (matriculaRepository.existsByAlunoId(id)){
            throw new ResponseStatusException
                    (HttpStatus.CONFLICT,"Não foi possível excluir o aluno porque existem matrículas vinculadas");
        }
        alunoRepository.deleteById(id);
    }

    @Transactional
    public AlunoResponseDTO update (Long id, AlunoRequestDTO dto){
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Aluno não encontrado"));
        aluno.setNome(dto.nome());
        aluno.setDataNascimento(dto.dataNascimento());
        aluno.setEmail(dto.email());
        alunoRepository.save(aluno);
        return AlunoResponseDTO.fromEntity(aluno);
    }
}

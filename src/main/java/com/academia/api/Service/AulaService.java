package com.academia.api.Service;

import com.academia.api.DTOS.AulaRequestDTO;
import com.academia.api.DTOS.AulaResponseDTO;
import com.academia.api.Model.Aula;
import com.academia.api.Model.Instrutor;
import com.academia.api.Repository.AulaRepository;
import com.academia.api.Repository.InstrutorRepository;
import com.academia.api.Repository.MatriculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.awt.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AulaService {
    private final AulaRepository aulaRepository;
    private final InstrutorRepository instrutorRepository;
    private final MatriculaRepository matriculaRepository;

    @Transactional(readOnly = true)
    public List<AulaResponseDTO> findAll(){
        return aulaRepository.findAll().stream().map(AulaResponseDTO::fromEntity).toList();
    }

    @Transactional(readOnly = true)
    public AulaResponseDTO findById(Long id){
        return AulaResponseDTO.fromEntity(aulaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Aula não encontrada")));
    }

    @Transactional
    public AulaResponseDTO save(AulaRequestDTO dto){
        Aula aula = new Aula();
        aula.setNome(dto.nome());
        aula.setHorario(dto.horario());
        Instrutor instrutor = instrutorRepository.findById(dto.instrutorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Instrutor não encontrado"));
        aula.setInstrutor(instrutor);

        aulaRepository.save(aula);
        return AulaResponseDTO.fromEntity(aula);
    }
    @Transactional
    public void deleteById(Long id){
        if (matriculaRepository.existsByAulaId(id)){
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Não foi possível excluir a aula porque existem matrículas vinculadas");
        }
        if (aulaRepository.existsById(id)) {
            aulaRepository.deleteById(id);
        }else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"A aula com esse ID não existe");
    }
}


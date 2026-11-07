package com.academia.api.Controller;

import com.academia.api.DTOS.AlunoRequestDTO;
import com.academia.api.DTOS.AlunoResponseDTO;
import com.academia.api.Service.AlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
public class AlunoController {
    private final AlunoService alunoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AlunoResponseDTO> listAll(){
        return alunoService.listAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlunoResponseDTO findById(@PathVariable Long id){
        return alunoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlunoResponseDTO save (@Valid @RequestBody AlunoRequestDTO dto){
        return alunoService.save(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable Long id){
        alunoService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlunoResponseDTO update(@PathVariable Long id, @Valid @RequestBody AlunoRequestDTO alunoRequestDTO){
        return alunoService.update(id,alunoRequestDTO);
    }

}

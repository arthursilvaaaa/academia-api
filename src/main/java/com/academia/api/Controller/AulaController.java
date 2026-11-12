package com.academia.api.Controller;

import com.academia.api.DTOS.AulaRequestDTO;
import com.academia.api.DTOS.AulaResponseDTO;
import com.academia.api.Service.AulaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aulas")
@RequiredArgsConstructor
public class AulaController {
    private final AulaService aulaService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AulaResponseDTO findById (@PathVariable Long id){
        return aulaService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AulaResponseDTO> findAll (){
        return aulaService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public AulaResponseDTO save (@RequestBody AulaRequestDTO dto){
        return aulaService.save(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        aulaService.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
        public AulaResponseDTO update(@PathVariable Long id, @RequestBody AulaRequestDTO dto){
            return aulaService.update(id, dto);
        }

}

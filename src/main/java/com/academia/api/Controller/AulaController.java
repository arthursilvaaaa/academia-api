package com.academia.api.Controller;

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
}

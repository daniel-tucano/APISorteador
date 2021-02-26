package com.zup.desafios.desafiosorteador.controllers;

import com.zup.desafios.desafiosorteador.DTOs.ApostaDTO;
import com.zup.desafios.desafiosorteador.models.Aposta;
import com.zup.desafios.desafiosorteador.services.ApostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/aposta")
@RestController
public class ApostaController {

    @Autowired
    private ApostaService apostaService;

    @GetMapping
    public Page<ApostaDTO> getApostas(Pageable pageable) {
        return apostaService.getApostas(pageable);
    }

    @GetMapping("/{idAposta}")
    public ResponseEntity<ApostaDTO> getAposta(@PathVariable Integer idAposta) {
        return apostaService.getAposta(idAposta);
    }

    @GetMapping("/email/{email}")
    public Page<ApostaDTO> getApostasByEmail( @PathVariable String email, Pageable pageable) {
        return apostaService.getApostasByEmail(email, pageable);
    }

    @PostMapping
    public ResponseEntity<ApostaDTO> addAposta(@RequestBody Aposta aposta) {
        return apostaService.addAposta(aposta);
    }

    @DeleteMapping("/{idAposta}")
    public ResponseEntity<ApostaDTO> deleteAposta(@PathVariable Integer idAposta) {
        return apostaService.deleteAposta(idAposta);
    }
}

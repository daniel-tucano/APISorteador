package com.zup.desafios.desafiosorteador.controllers;

import com.zup.desafios.desafiosorteador.models.Sorteio;
import com.zup.desafios.desafiosorteador.services.SorteioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/sorteio")
@RestController
public class SorteioController {

    @Autowired
    private SorteioService sorteioService;

    @GetMapping
    public Page<Sorteio> getSorteios(Pageable pageable) {
        return sorteioService.getSorteios(pageable);
    }

    @GetMapping("/{idSorteio}")
    public ResponseEntity<Sorteio> getSorteio(@PathVariable Integer idSorteio) {
        return sorteioService.getSorteio(idSorteio);
    }

    @PostMapping
    public ResponseEntity<Sorteio> addSorteio(@RequestBody Sorteio sorteio) {
        return sorteioService.addSorteio(sorteio);
    }

    @DeleteMapping("/{idSorteio}")
    public ResponseEntity<Sorteio> deleteSorteio(@PathVariable Integer idSorteio) {
        return sorteioService.deleteSorteio(idSorteio);
    }
}

package com.zup.desafios.desafiosorteador.services;

import com.zup.desafios.desafiosorteador.models.Sorteio;
import com.zup.desafios.desafiosorteador.repositories.ApostaRepository;
import com.zup.desafios.desafiosorteador.repositories.SorteioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SorteioService {

    @Autowired
    private SorteioRepository sorteioRepository;

    @Autowired
    private ApostaRepository apostaRepository;

    public Page<Sorteio> getSorteios(Pageable pageable) {
        return sorteioRepository.findAll(pageable);
    }

    public ResponseEntity<Sorteio> getSorteio(Integer idSorteio) {
        Optional<Sorteio> sorteio = sorteioRepository.findById(idSorteio);
        return sorteio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    public ResponseEntity<Sorteio> addSorteio(Sorteio sorteio) {
        Sorteio sorteioToAdd = sorteioRepository.save(sorteio);
        return ResponseEntity.status(HttpStatus.CREATED).body(sorteioToAdd);
    }

    public ResponseEntity<Sorteio> deleteSorteio(Integer idSorteio) {
        Optional<Sorteio> sorteioToDelete = sorteioRepository.findById(idSorteio);
        if (sorteioToDelete.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        sorteioRepository.deleteById(idSorteio);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}

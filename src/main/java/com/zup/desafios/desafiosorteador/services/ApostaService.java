package com.zup.desafios.desafiosorteador.services;

import com.zup.desafios.desafiosorteador.DTOs.ApostaDTO;
import com.zup.desafios.desafiosorteador.models.Aposta;
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
import java.util.stream.Collectors;


@Service
public class ApostaService {
    @Autowired
    private SorteioRepository sorteioRepository;

    @Autowired
    private ApostaRepository apostaRepository;

    @Autowired
    private NumerosApostadosService numerosApostadosService;

    public Page<ApostaDTO> getApostas(Pageable pageable) {
        Page<Aposta> pageApostas = apostaRepository.findAll(pageable);
        apostaRepository.findApostasNumerosApostados(pageApostas.stream().collect(Collectors.toList()));
        return pageApostas.map(ApostaDTO::new);
    }

    public Page<ApostaDTO> getApostasByEmail(String email, Pageable pageable) {
        Page<Aposta> pageApostasByEmail = apostaRepository.findAllByEmail(email,pageable);
        apostaRepository.findApostasNumerosApostadosByEmail(email, pageApostasByEmail.stream().collect(Collectors.toList()));
        return pageApostasByEmail.map(ApostaDTO::new);
    }

    public ResponseEntity<ApostaDTO> getAposta(Integer idAposta) {
        Optional<Aposta> aposta = apostaRepository.findById(idAposta);
        if (aposta.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.ok(new ApostaDTO(aposta.get()));
    }

    public ResponseEntity<ApostaDTO> addAposta(Aposta aposta) {
        if (aposta.getSorteio() == null) {
            Optional<Sorteio> latestSorteio = sorteioRepository.findLatestSorteio();
            if (latestSorteio.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            aposta.setSorteio(latestSorteio.get());
        }
        aposta.setNumerosApostados(numerosApostadosService.geraNumerosApostados(aposta));
        apostaRepository.save(aposta);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApostaDTO(aposta));
    }

    public ResponseEntity<ApostaDTO> deleteAposta(Integer idAposta) {
        Optional<Aposta> apostaToDelete = apostaRepository.findById(idAposta);
        if ( apostaToDelete.isEmpty() ) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        apostaRepository.deleteById(idAposta);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}

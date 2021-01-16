package com.icetec.yurileader.vagasdevapi.controller;

import com.icetec.yurileader.vagasdevapi.event.RecursoCriadoEvent;
import com.icetec.yurileader.vagasdevapi.model.Candidato;
import com.icetec.yurileader.vagasdevapi.repository.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/candidatos")
public class CandidatoController {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private CandidatoRepository candidatoRepository;

    @GetMapping
    public List<Candidato> listar() {
        return candidatoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidato> buscarPorId(@PathVariable Long id) {

        return candidatoRepository.findById(id)
                .map(candidato -> ResponseEntity.ok(candidato))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Candidato> criarCandidato(@Valid @RequestBody Candidato candidato, HttpServletResponse response) {
        Candidato candidatoSalvo = candidatoRepository.save(candidato);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, candidatoSalvo.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(candidatoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidato> atualizar(@Valid @PathVariable Long id) {
        Optional<Candidato> byId = candidatoRepository.findById(id);

        return null;
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        candidatoRepository.deleteById(id);
    }

}

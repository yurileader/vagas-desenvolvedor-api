package com.icetec.yurileader.vagasdevapi.controller;

import com.icetec.yurileader.vagasdevapi.event.RecursoCriadoEvent;
import com.icetec.yurileader.vagasdevapi.model.Candidato;
import com.icetec.yurileader.vagasdevapi.repository.CandidatoRepository;
import com.icetec.yurileader.vagasdevapi.service.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/candidatos")
public class CandidatoController {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private CandidatoService candidatoService;

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
    public ResponseEntity<Candidato> atualizar(@Valid @PathVariable Long id, @Valid @RequestBody Candidato candidato) {
        Candidato candidatoSalvo = candidatoService.candidatoAtualizar(id, candidato);
        return ResponseEntity.ok(candidatoSalvo);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        candidatoRepository.deleteById(id);
    }

}

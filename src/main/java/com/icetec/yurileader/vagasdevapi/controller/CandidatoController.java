package com.icetec.yurileader.vagasdevapi.controller;

import com.icetec.yurileader.vagasdevapi.model.Candidato;
import com.icetec.yurileader.vagasdevapi.repository.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/candidatos")
public class CandidatoController {

    @Autowired
    private CandidatoRepository candidatoRepository;

    @GetMapping
    public List<Candidato> listar() {
        return candidatoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidato> buscarPorId(@PathVariable Long id) {

        return candidatoRepository.findById(id)
                .map(categoria -> ResponseEntity.ok(categoria))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Candidato> criar(@RequestBody Candidato candidato, HttpServletResponse response) {
        Candidato categoriaSalva = candidatoRepository.save(candidato);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(categoriaSalva.getId()).toUri();
        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(categoriaSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidato> atualizar (@PathVariable Long id){
        Optional<Candidato> byId = candidatoRepository.findById(id);

        return null;
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id){
        candidatoRepository.deleteById(id);
    }

}

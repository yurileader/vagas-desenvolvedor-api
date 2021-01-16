package com.icetec.yurileader.vagasdevapi.controller;

import com.icetec.yurileader.vagasdevapi.event.RecursoCriadoEvent;
import com.icetec.yurileader.vagasdevapi.model.Tecnologia;
import com.icetec.yurileader.vagasdevapi.repository.TecnologiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/tecnologias")
public class TecnologiaController {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private TecnologiaRepository tecnologiaRepository;

    @GetMapping
    public List<Tecnologia> listar() {
        return tecnologiaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tecnologia> buscarPorId(@PathVariable Long id) {

        return tecnologiaRepository.findById(id)
                .map(tecnologia -> ResponseEntity.ok(tecnologia))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Tecnologia> criarTecnologia(@RequestBody Tecnologia tecnologia, HttpServletResponse response) {
        Tecnologia tecnologiaSalva = tecnologiaRepository.save(tecnologia);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, tecnologiaSalva.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(tecnologiaSalva);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        tecnologiaRepository.deleteById(id);
    }

}

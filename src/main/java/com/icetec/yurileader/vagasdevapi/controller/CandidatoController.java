package com.icetec.yurileader.vagasdevapi.controller;

import com.icetec.yurileader.vagasdevapi.event.RecursoCriadoEvent;
import com.icetec.yurileader.vagasdevapi.model.dto.CandidatoDTO;
import com.icetec.yurileader.vagasdevapi.model.dto.CandidatoListagemDTO;
import com.icetec.yurileader.vagasdevapi.service.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private CandidatoService candidatoService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CANDIDATO') and #oauth2.hasScope('read')")
    public List<CandidatoDTO> listar() {
        return candidatoService.listarTodos();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CANDIDATO') and #oauth2.hasScope('read')")
    public ResponseEntity<CandidatoListagemDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(candidatoService.listarPorId(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_CANDIDATO') and #oauth2.hasScope('write')")
    public ResponseEntity<CandidatoDTO> criarCandidato(@Valid @RequestBody CandidatoDTO candidatoDTO, HttpServletResponse response) {
        CandidatoDTO candidatoSalvo = candidatoService.candidatoSalvar(candidatoDTO);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, candidatoSalvo.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(candidatoSalvo);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_CANDIDATO') and #oauth2.hasScope('write')")
    public ResponseEntity<CandidatoDTO> atualizar(@Valid @PathVariable Long id, @Valid @RequestBody CandidatoDTO candidatoDTO) {
        CandidatoDTO candidatoSalvo = candidatoService.candidatoAtualizar(id, candidatoDTO);
        return ResponseEntity.ok(candidatoSalvo);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_REMOVER_CANDIDATO') and #oauth2.hasScope('write')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        candidatoService.candidatoDeletar(id);
    }


}

package com.icetec.yurileader.vagasdevapi.controller;

import com.icetec.yurileader.vagasdevapi.event.RecursoCriadoEvent;
import com.icetec.yurileader.vagasdevapi.model.dto.TecnologiaDTO;
import com.icetec.yurileader.vagasdevapi.service.TecnologiaService;
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
@RequestMapping("/tecnologias")
public class TecnologiaController {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private TecnologiaService tecnologiaService;


    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_TECNOLOGIA') and #oauth2.hasScope('read')")
    public List<TecnologiaDTO> listar() {
        return tecnologiaService.listarTodos();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_TECNOLOGIA') and #oauth2.hasScope('read')")
    public ResponseEntity<TecnologiaDTO> buscarPorId(@PathVariable Long id) {

        return ResponseEntity.ok(tecnologiaService.listarPorId(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_TECNOLOGIA') and #oauth2.hasScope('write')")
    public ResponseEntity<TecnologiaDTO> criarTecnologia(@Valid @RequestBody TecnologiaDTO tecnologiaDTO, HttpServletResponse response) {

        TecnologiaDTO tecnologiaSalva = tecnologiaService.tecnologiaSalvar(tecnologiaDTO);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, tecnologiaSalva.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(tecnologiaSalva);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_REMOVER_TECNOLOGIA') and #oauth2.hasScope('write')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        tecnologiaService.tecnologiaDeletar(id);
    }

}

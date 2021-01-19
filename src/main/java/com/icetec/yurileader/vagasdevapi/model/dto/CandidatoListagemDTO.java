package com.icetec.yurileader.vagasdevapi.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CandidatoListagemDTO {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String urlLinkedin;
    private String urlGithub;
    private List<TecnologiaDTO> tecnologiaDTOS = new ArrayList<>();
}

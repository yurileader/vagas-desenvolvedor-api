package com.icetec.yurileader.vagasdevapi.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CandidatoDTO {   // DTO usado para salvar e editar um candidato

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private LocalDate dataNascimento;
    private String urlLinkedin;
    private String urlGithub;
    private List<TecnologiaDTO> tecnologias = new ArrayList<>();
}

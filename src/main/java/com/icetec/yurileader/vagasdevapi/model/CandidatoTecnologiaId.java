package com.icetec.yurileader.vagasdevapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class CandidatoTecnologiaId  implements Serializable {

    private Long idCandidato;

    private Long idTecnologia;
}

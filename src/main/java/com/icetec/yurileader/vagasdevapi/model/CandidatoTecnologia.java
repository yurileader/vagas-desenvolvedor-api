package com.icetec.yurileader.vagasdevapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "CANDIDATO_TECNOLOGIA")
@Getter
@Setter
@NoArgsConstructor
@IdClass(CandidatoTecnologiaId.class)
public class CandidatoTecnologia {

    @Id
    @Column(name = "ID_CANDIDATO")
    private Long idCandidato;

    @Id
    @Column(name = "ID_TECNOLOGIA")
    private Long idTecnologia;
}

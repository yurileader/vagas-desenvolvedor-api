package com.icetec.yurileader.vagasdevapi.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "CANDIDATO")
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @Column(name = "NOME", nullable = false)
    private String nome;

    @NotNull
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @NotNull
    @Column(name = "TELEFONE")
    private String telefone;

    @NotNull
    @Column(name = "DATA_NASCIMENTO", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "URL_LINKEDIN")
    private String urlLinkedin;

    @Column(name = "URL_GITHUB")
    private String urlGithub;


}


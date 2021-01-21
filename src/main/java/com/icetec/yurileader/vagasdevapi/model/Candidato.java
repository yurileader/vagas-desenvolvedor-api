package com.icetec.yurileader.vagasdevapi.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @Size(min = 3, max = 70)
    @Column(name = "NOME", nullable = false)
    private String nome;

    @Size(min = 5, max = 100)
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Size(min = 8, max = 20)
    @Column(name = "TELEFONE")
    private String telefone;

    @NotNull
    @Column(name = "DATA_NASCIMENTO", nullable = false)
    private LocalDate dataNascimento;

    @Size(max = 100)
    @Column(name = "URL_LINKEDIN")
    private String urlLinkedin;

    @Size(max = 100)
    @Column(name = "URL_GITHUB")
    private String urlGithub;
}


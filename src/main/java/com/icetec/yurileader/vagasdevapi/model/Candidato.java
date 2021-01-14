package com.icetec.yurileader.vagasdevapi.model;

import com.sun.istack.Nullable;
import lombok.*;

import javax.persistence.*;
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

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "TELEFONE")
    private String telefone;

    @Column(name = "DATA_NASCIMENTO", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "URL_LINKEDIN")
    private String urlLinkedin;

    @Column(name = "URL_GITHUB")
    private String urlGithub;


}


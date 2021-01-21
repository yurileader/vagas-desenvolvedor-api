package com.icetec.yurileader.vagasdevapi.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "USUARIO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    @EqualsAndHashCode.Include
    private Long id;

    @Size(min = 3, max = 70)
    @Column(name = "NOME", nullable = false)
    private String nome;

    @NotNull
    @Size(max = 100)
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @NotNull
    @Size(max = 150)
    @Column(name = "SENHA", nullable = false)
    private String senha;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USUARIO_PERMISSAO", joinColumns = @JoinColumn(name = "ID_USUARIO")
            , inverseJoinColumns = @JoinColumn(name = "D_PERMISSAO"))
    private List<Permissao> permissoes;
}

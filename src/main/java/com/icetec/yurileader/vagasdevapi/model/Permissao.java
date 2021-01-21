package com.icetec.yurileader.vagasdevapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PERMISSAO")
@Getter
@Setter
@NoArgsConstructor
public class Permissao {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;
}

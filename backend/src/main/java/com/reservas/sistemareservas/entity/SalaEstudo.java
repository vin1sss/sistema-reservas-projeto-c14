package com.reservas.sistemareservas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "salas_estudo")
@PrimaryKeyJoinColumn(name = "recurso_id")
@DiscriminatorValue("SALA_ESTUDO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SalaEstudo extends Recurso {

    @Column(name = "capacidade_pessoas", nullable = false)
    private Integer capacidadePessoas;

    @Column(name = "possui_projetor", nullable = false)
    private boolean possuiProjetor;
}

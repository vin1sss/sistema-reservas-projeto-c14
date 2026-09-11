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
@Table(name = "quadras")
@PrimaryKeyJoinColumn(name = "recurso_id")
@DiscriminatorValue("QUADRA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Quadra extends Recurso {

    @Column(name = "modalidade", nullable = false)
    private String modalidade;

    @Column(name = "coberta", nullable = false)
    private boolean coberta;
}

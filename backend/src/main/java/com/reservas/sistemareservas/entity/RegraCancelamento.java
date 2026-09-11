package com.reservas.sistemareservas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "regras_cancelamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegraCancelamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "recurso_id", nullable = false, unique = true)
    private Recurso recurso;

    @Column(name = "horas_minimas_antecedencia", nullable = false)
    private Integer horasMinimasAntecedencia;

    @Column(name = "percentual_multa", nullable = false)
    private Double percentualMulta;

    @Column(name = "permite_cancelamento", nullable = false)
    private boolean permiteCancelamento;

    @Column(name = "observacoes")
    private String observacoes;
}

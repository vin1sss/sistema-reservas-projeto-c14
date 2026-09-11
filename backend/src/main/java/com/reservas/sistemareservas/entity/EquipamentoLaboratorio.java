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
@Table(name = "equipamentos_laboratorio")
@PrimaryKeyJoinColumn(name = "recurso_id")
@DiscriminatorValue("EQUIPAMENTO_LABORATORIO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class EquipamentoLaboratorio extends Recurso {

    @Column(name = "laboratorio", nullable = false)
    private String laboratorio;

    @Column(name = "numero_patrimonio", nullable = false, unique = true)
    private String numeroPatrimonio;
}

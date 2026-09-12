package com.reservas.sistemareservas.service;

import java.time.LocalDateTime;

/**
 * Valida intervalos de reservas sem acessar o banco de dados.
 * A comparação é apenas temporal; recurso e status devem ser
 * considerados pela camada que utilizar este validador.
 */
public final class ValidadorHorarioReserva {

    public void validarPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        if (inicio == null || fim == null) {
            throw new IllegalArgumentException(
                    "O início e o fim da reserva são obrigatórios.");
        }
        if (!fim.isAfter(inicio)) {
            throw new IllegalArgumentException(
                    "O fim da reserva deve ser posterior ao início.");
        }
    }

    /**
     * Considera intervalos [início, fim): horários consecutivos,
     * como 10h–11h e 11h–12h, não se sobrepõem.
     */
    public boolean haSobreposicao(
            LocalDateTime inicioA, LocalDateTime fimA,
            LocalDateTime inicioB, LocalDateTime fimB) {

        validarPeriodo(inicioA, fimA);
        validarPeriodo(inicioB, fimB);
        return inicioA.isBefore(fimB) && inicioB.isBefore(fimA);
    }
}
package com.reservas.sistemareservas.service;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidadorHorarioReservaTest {

    private final ValidadorHorarioReserva validador =
            new ValidadorHorarioReserva();

    private LocalDateTime horario(int hora) {
        return LocalDateTime.of(2026, 9, 12, hora, 0);
    }

    @Test
    void aceitaPeriodoValido() {
        assertDoesNotThrow(() ->
                validador.validarPeriodo(horario(10), horario(11)));
    }

    @Test
    void rejeitaDatasAusentes() {
        assertThrows(IllegalArgumentException.class, () ->
                validador.validarPeriodo(null, horario(11)));
        assertThrows(IllegalArgumentException.class, () ->
                validador.validarPeriodo(horario(10), null));
        assertThrows(IllegalArgumentException.class, () ->
                validador.validarPeriodo(null, null));
    }

    @Test
    void rejeitaFimIgualOuAnteriorAoInicio() {
        assertThrows(IllegalArgumentException.class, () ->
                validador.validarPeriodo(horario(10), horario(10)));
        assertThrows(IllegalArgumentException.class, () ->
                validador.validarPeriodo(horario(11), horario(10)));
    }

    @ParameterizedTest
    @CsvSource({
        "10, 12, 11, 13, true",
        "11, 13, 10, 12, true",
        "10, 14, 11, 12, true",
        "11, 12, 10, 14, true",
        "10, 12, 10, 12, true",
        "10, 12, 10, 11, true",
        "10, 12, 11, 12, true",
        "10, 11, 12, 13, false",
        "12, 13, 10, 11, false",
        "10, 11, 11, 12, false",
        "11, 12, 10, 11, false"
    })
    void comparaIntervalos(
            int inicioA, int fimA, int inicioB, int fimB,
            boolean esperado) {
        assertEquals(esperado, validador.haSobreposicao(
                horario(inicioA), horario(fimA),
                horario(inicioB), horario(fimB)));
    }

    @Test
    void detectaSobreposicaoEntreDias() {
        LocalDateTime inicio = horario(23);
        LocalDateTime fim = inicio.plusHours(2);

        assertDoesNotThrow(() -> validador.validarPeriodo(inicio, fim));
        assertTrue(validador.haSobreposicao(
                inicio, fim, inicio.plusMinutes(30), fim.plusHours(1)));
    }

    @Test
    void validaOsDoisPeriodosAntesDeComparar() {
        assertThrows(IllegalArgumentException.class, () ->
                validador.haSobreposicao(
                        horario(12), horario(10), horario(13), horario(14)));
        assertThrows(IllegalArgumentException.class, () ->
                validador.haSobreposicao(
                        horario(10), horario(11), horario(13), horario(12)));
        assertThrows(IllegalArgumentException.class, () ->
                validador.haSobreposicao(
                        horario(10), horario(11), null, horario(12)));
    }
}
package br.com.diogotour.milhas.domain;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ItinerarioTest {

    @Test
    void existeVooDeveRetornarTrueParaCodigosVooIguais() {
        Voo voo1 = new Voo();
        ReflectionTestUtils.setField(voo1, "codigo", "VOO1");
        ReflectionTestUtils.setField(voo1, "horaPartida", LocalDateTime.of(2022,10,31,12,0));
        Itinerario itinerario = new Itinerario(voo1);

        Voo voo2 = new Voo();
        ReflectionTestUtils.setField(voo2, "codigo", "VOO1");
        ReflectionTestUtils.setField(voo2, "horaPartida", LocalDateTime.of(2022,10,31,12,0));

        assertTrue(itinerario.existeVoo(voo2));

    }

    @Test
    void existeVooDeveRetornarFalseParaCodigosVooDiferentes() {
        Voo voo1 = new Voo();
        ReflectionTestUtils.setField(voo1, "codigo", "VOO1");
        ReflectionTestUtils.setField(voo1, "horaPartida", LocalDateTime.of(2022,10,31,12,0));
        Itinerario itinerario = new Itinerario(voo1);

        Voo voo2 = new Voo();
        ReflectionTestUtils.setField(voo2, "codigo", "VOO2");
        ReflectionTestUtils.setField(voo2, "horaPartida", LocalDateTime.of(2022,10,31,12,0));

        assertFalse(itinerario.existeVoo(voo2));

    }
}
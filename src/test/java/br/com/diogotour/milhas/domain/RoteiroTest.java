package br.com.diogotour.milhas.domain;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class RoteiroTest {

    @Test
    public void adicionaItinerarioSeNaoExistir() {
        Voo voo1 = new Voo();
        ReflectionTestUtils.setField(voo1, "codigo", "XPTO");
        ReflectionTestUtils.setField(voo1, "horaPartida", LocalDateTime.of(2022,10,31,12,0));
        Voo voo2 = new Voo();
        ReflectionTestUtils.setField(voo2, "codigo", "XPTO1");
        ReflectionTestUtils.setField(voo2, "horaPartida", LocalDateTime.of(2022,11,1,12,0));

        Itinerario ida1 = new Itinerario(voo1);
        Itinerario ida2 = new Itinerario(voo2);

        Voo vooVolta = new Voo();
        ReflectionTestUtils.setField(vooVolta, "horaPartida", LocalDateTime.of(2022,11,2,12,0));
        Itinerario volta = new Itinerario(vooVolta);

        Roteiro roteiro = new Roteiro(ida1, volta);
        roteiro.adicionarItinerarios(ida2, volta);

        assertEquals(2, roteiro.getOpcoesIda().size());
    }

    @Test
    public void naoAdicionaItinerarioSeExistir() {
        Voo voo1 = new Voo();
        ReflectionTestUtils.setField(voo1, "codigo", "XPTO");
        ReflectionTestUtils.setField(voo1, "horaPartida", LocalDateTime.of(2022,10,31,12,0));
        Voo voo2 = new Voo();
        ReflectionTestUtils.setField(voo2, "codigo", "XPTO");
        ReflectionTestUtils.setField(voo2, "horaPartida", LocalDateTime.of(2022,11,1,12,0));

        Itinerario ida1 = new Itinerario(voo1);
        Itinerario ida2 = new Itinerario(voo2);

        Voo vooVolta = new Voo();
        ReflectionTestUtils.setField(vooVolta, "horaPartida", LocalDateTime.of(2022,11,2,12,0));
        Itinerario volta = new Itinerario(vooVolta);

        Roteiro roteiro = new Roteiro(ida1, volta);

        roteiro.adicionarItinerarios(ida2, volta);

        assertEquals(1, roteiro.getOpcoesIda().size());
    }

}
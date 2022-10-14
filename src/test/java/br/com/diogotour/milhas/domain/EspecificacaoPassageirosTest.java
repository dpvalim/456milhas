package br.com.diogotour.milhas.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class EspecificacaoPassageirosTest {

    EspecificacaoPassageiros passageiros;

    @BeforeEach
    void setUp() {
        passageiros = new EspecificacaoPassageiros();
        passageiros.especificacao = new HashMap<>();
        passageiros.especificacao.put(TipoPassageiro.ADULTO, 2);
        passageiros.especificacao.put(TipoPassageiro.CRIANCA, 1);
        passageiros.especificacao.put(TipoPassageiro.BEBE, 1);
    }

    @Test
    void getQtdPassageiros() {
        assertEquals(new BigDecimal(4), passageiros.getQtdPassageiros());
    }

    @Test
    void separadasPorTipo() {
        assertEquals(3, passageiros.separadasPorTipo().size());
    }

    @Test
    void testGetQtdPassageiros() {
        assertEquals(2, passageiros.getQtdPassageiros(TipoPassageiro.ADULTO));
        assertEquals(1, passageiros.getQtdPassageiros(TipoPassageiro.CRIANCA));
        assertEquals(1, passageiros.getQtdPassageiros(TipoPassageiro.BEBE));
        assertEquals(2, passageiros.getQtdPassageiros(TipoPassageiro.BEBE, TipoPassageiro.CRIANCA));
        assertEquals(3, passageiros.getQtdPassageiros(TipoPassageiro.CRIANCA, TipoPassageiro.ADULTO));
        assertEquals(3, passageiros.getQtdPassageiros(TipoPassageiro.BEBE, TipoPassageiro.ADULTO));
        assertEquals(4, passageiros.getQtdPassageiros(TipoPassageiro.BEBE, TipoPassageiro.ADULTO, TipoPassageiro.CRIANCA));
        assertEquals(4, passageiros.getQtdPassageiros(null));
    }
}
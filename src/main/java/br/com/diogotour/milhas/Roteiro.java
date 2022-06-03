package br.com.diogotour.milhas;

import java.math.BigDecimal;
import java.util.List;

public class Roteiro {

    List<Voo> voosIda;
    List<Voo> voosVolta;

    List<Voo> getVoosIda() {
        return voosIda;
    }

    public List<Voo> getVoosVolta() {
        return voosVolta;
    }

    BigDecimal getTaxaEmbarque(EspecificacaoPassageiros especificacaoPassageiros) {

    }
}

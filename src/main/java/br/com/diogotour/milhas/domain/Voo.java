package br.com.diogotour.milhas.domain;

import java.math.BigDecimal;

public class Voo extends Acontecimento {

    String codigo;
    CiaAerea ciaAerea;
    Aeroporto localEmbarque;
    Aeroporto localDesembarque;

    String getNomeCiaAerea() {
        return this.ciaAerea.getNome();
    }

    BigDecimal getTaxaEmbarque(EspecificacaoPassageiros especificacaoPassageiros) {
        return localEmbarque.getTaxaEmbarque(especificacaoPassageiros);
    }

    String getCodigoIATAEmbarque() {
        return localEmbarque.getCodigoIATA();
    }

    String getLocalEmbarque() {
        return this.localEmbarque.getCidade();
    }

    String getNomeAeroportoEmbarque() {
        return this.localEmbarque.getNome();
    }

    String getLocalDesembarque() {
        return this.localDesembarque.getCidade();
    }

    String getNomeAeroportoDesembarque() {
        return this.localDesembarque.getNome();
    }

    String getCodigoIATADesembarque() {
        return localEmbarque.getCodigoIATA();
    }

}


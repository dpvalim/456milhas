package br.com.diogotour.milhas;

import java.math.BigDecimal;

public class Voo {

    LocalEmbarque localEmbarque;

    //  Calcula preco de acordo com o tipo de passageiro
    BigDecimal getPreco(TipoPassageiro tipoPassageiro) {
        return null;
    }

    BigDecimal getTaxaEmbarque(EspecificacaoPassageiros especificacaoPassageiros) {
        return localEmbarque.getTaxaEmbarque(especificacaoPassageiros);
    }

}

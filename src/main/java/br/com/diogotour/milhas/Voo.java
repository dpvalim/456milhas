package br.com.diogotour.milhas;

import java.math.BigDecimal;
import java.util.List;

public class Voo {

    String codigo;
    LocalEmbarque localEmbarque;
    BigDecimal preco;
    List<Voo> conexoes;

    //  Calcula preco de acordo com o tipo de passageiro
    BigDecimal getPreco(TipoPassageiro tipoPassageiro) {
        return null;
    }

    BigDecimal getTaxaEmbarque(EspecificacaoPassageiros especificacaoPassageiros) {
        return localEmbarque.getTaxaEmbarque(especificacaoPassageiros);
    }


}


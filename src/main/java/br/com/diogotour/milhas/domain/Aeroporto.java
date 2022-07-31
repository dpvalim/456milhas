package br.com.diogotour.milhas.domain;

import java.math.BigDecimal;

public class Aeroporto {

    String codigoIATA;
    String nome;
    Localidade localidade;
    BigDecimal taxa;

    BigDecimal getTaxaEmbarque(EspecificacaoPassageiros especificacaoPassageiros) {
        return taxa.multiply(especificacaoPassageiros.getQtdPassageiros());
    }

    public String getCodigoIATA() {
        return codigoIATA;
    }

    public String getNome() {
        return nome;
    }

    public String getCidade() {
        return localidade.getCidade();
    }
}

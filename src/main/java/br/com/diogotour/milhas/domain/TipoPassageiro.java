package br.com.diogotour.milhas.domain;

import java.math.BigDecimal;

public enum TipoPassageiro {
    ADULTO(1), CRIANCA(0.7), BEBE(0.5);

    private BigDecimal multiplicador;

    TipoPassageiro(double multiplicador) {
        this.multiplicador = new BigDecimal(multiplicador);
    }

    public BigDecimal calcularPreco(BigDecimal preco) {
        return preco.multiply(this.multiplicador);
    }
}

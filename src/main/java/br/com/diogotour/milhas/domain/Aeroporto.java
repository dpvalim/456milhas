package br.com.diogotour.milhas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
public class Aeroporto {

    @Id
    @Column(length = 4)
    String codigoIATA;
    String nome;
    @OneToOne
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

package br.com.diogotour.milhas.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Voo extends Acontecimento {

    @Id
    @Column(length = 6)
    String codigo;
    @OneToOne
    CiaAerea ciaAerea;
    @ManyToOne
    @JoinColumn(name = "cod_iata_emb")
    Aeroporto localEmbarque;
    @ManyToOne
    @JoinColumn(name = "cod_iata_desemb")
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


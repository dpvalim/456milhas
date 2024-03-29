package br.com.diogotour.milhas.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@Entity
public class Voo extends Acontecimento {

    @Id
    @Column(length = 6)
    String codigo;
    @ManyToOne
    @JoinColumn(name = "cod_cia_aerea")
    CiaAerea ciaAerea;
    @ManyToOne
    @JoinColumn(name = "cod_iata_emb")
    Aeroporto localEmbarque;
    @ManyToOne
    @JoinColumn(name = "cod_iata_desemb")
    Aeroporto localDesembarque;

    Integer qtdTotalAssentos;

    @Column(name = "qtd_ass_disp_adulto")
    Integer qtdAssentosDisponiveisAdulto;

    @Column(name = "qtd_ass_disp_bebe")
    Integer qtdAssentosDisponiveisBebe;

    private BigDecimal preco;

    public BigDecimal calcularPreco(TipoPassageiro tipoPassageiro) {
        if (tipoPassageiro != null) {
            return tipoPassageiro.calcularPreco(this.preco);
        }
        return TipoPassageiro.ADULTO.calcularPreco(this.preco);
    }

    public BigDecimal somaPreco(Voo voo) {
        if (Objects.equals(this, voo)) return this.preco;

        return this.preco.add(voo.preco);
    }

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

    @Override
    public String toString() {
        return "Voo{" +
                "codigo='" + codigo + '\'' +
                ", ciaAerea=" + ciaAerea.getNome() +
                ", localEmbarque=" + localEmbarque.getNome() +
                ", localDesembarque=" + localDesembarque.getNome() +
                ", qtdTotalAssentos=" + qtdTotalAssentos +
                ", qtdAssentosDisponiveisAdulto=" + qtdAssentosDisponiveisAdulto +
                ", qtdAssentosDisponiveisBebe=" + qtdAssentosDisponiveisBebe +
                ", horaPartida=" + horaPartida +
                ", horaChegada=" + horaChegada +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voo voo = (Voo) o;
        return codigo.equals(voo.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}


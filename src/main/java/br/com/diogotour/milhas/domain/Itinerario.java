package br.com.diogotour.milhas.domain;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

public class Itinerario implements Comparable<Itinerario>{

    Integer qtdBagagens = 1;
    SortedSet<Acontecimento> conexoes = new TreeSet<>();

    public Itinerario(final Voo voo) {
        super();
        this.conexoes.add(voo);
    }

    //  Calcula preco de acordo com o tipo de passageiro
    BigDecimal getPreco(TipoPassageiro tipoPassageiro) {
        return this.conexoes.stream()
                .filter(Voo.class::isInstance)
                .map(Voo.class::cast)
                .map(voo -> voo.calcularPreco(tipoPassageiro))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal somaPreco(Itinerario outro) {
        if (Objects.equals(this, outro)) return this.getPreco(TipoPassageiro.ADULTO);

        return this.getPreco(TipoPassageiro.ADULTO).add(outro.getPreco(TipoPassageiro.ADULTO));
    }

    Integer getQtdBagagens() {
        return this.qtdBagagens;
    }

    BigDecimal getTaxaEmbarque(EspecificacaoPassageiros especificacaoPassageiros) {
        return this.conexoes.stream()
                .filter(Voo.class::isInstance)
                .map(Voo.class::cast)
                .map(voo -> voo.getTaxaEmbarque(especificacaoPassageiros))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public String getNomeCiaAerea() {
        return getPrimeiroVoo().getNomeCiaAerea();
    }

    public LocalDateTime getHoraEmbarque() {
        return getPrimeiroVoo().getHoraEmbarque();
    }

    public String getLocalEmbarque() {
        return getPrimeiroVoo().getLocalEmbarque();
    }

    public String getCodigoAeroportoEmbarque() {
        return getPrimeiroVoo().getCodigoIATAEmbarque();
    }

    public String getNomeAeroportoEmbarque() {
        return getPrimeiroVoo().getNomeAeroportoEmbarque();
    }

    public LocalDateTime getHoraDesembarque() {
        return getUltimoVoo().getHoraDesembarque();
    }

    public String getLocalDesembarque() {
        return getUltimoVoo().getLocalDesembarque();
    }

    public String getCodigoAeroportoDesembarque() {
        return getUltimoVoo().getCodigoIATADesembarque();
    }

    public String getNomeAeroportoDesembarque() {
        return getUltimoVoo().getNomeAeroportoDesembarque();
    }

    public Voo getPrimeiroVoo() {
        return (Voo) this.conexoes.first();
    }

    public Voo getUltimoVoo() {
        return (Voo) this.conexoes.last();
    }

    public Long getQtdParadas() {
        return this.conexoes.stream()
                .filter(obj -> !(obj instanceof Voo))
                .count();
    }

    public String getDuracaoTotal() {
        Duration total = this.conexoes.stream().map(Acontecimento::getDuracao).reduce(Duration.ZERO, Duration::plus);
        return total.toHoursPart() + "h " + total.toMinutesPart() + "m";
    }

    public boolean existeVoo(Voo voo) {
        for (Acontecimento v : this.conexoes) {
            if (((Voo)v).equals(voo)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Itinerario{" +
                "preco=" + getPreco(null) +
                ", qtdBagagens=" + qtdBagagens +
                ", conexoes=" + conexoes +
                '}';
    }

    @Override
    public int compareTo(Itinerario itinerario) {
        return this.getHoraEmbarque().compareTo(itinerario.getHoraEmbarque());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Itinerario that = (Itinerario) o;
        return qtdBagagens.equals(that.qtdBagagens)
                && conexoes.containsAll(that.conexoes)
                && that.conexoes.containsAll(conexoes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(qtdBagagens, conexoes);
    }
}


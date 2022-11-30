package br.com.diogotour.milhas.domain;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Roteiro implements Comparable<Roteiro>{

    Set<Itinerario> itinerariosIda = new TreeSet<>();
    Set<Itinerario> itinerariosVolta = new TreeSet<>();

    BigDecimal taxaDeServico = new BigDecimal(50);

    public Roteiro(Itinerario itinerarioIda, Itinerario itinerarioVolta) {
        super();
        this.itinerariosIda.add(itinerarioIda);
        this.itinerariosVolta.add(itinerarioVolta);
    }

    Set<Itinerario> getOpcoesIda() {
        return itinerariosIda;
    }

    public Set<Itinerario> getOpcoesVolta() {
        return itinerariosVolta;
    }

    private Itinerario getVooVolta() {
        return getOpcoesVolta().stream().findFirst().get();
    }

    private Itinerario getVooIda() {
        return getOpcoesIda().stream().findFirst().get();
    }

    // obter total de taxa de servico por quantidade de passageiros
    public BigDecimal getTaxaServico(EspecificacaoPassageiros especificacaoPassageiros) {
        return taxaDeServico.multiply(especificacaoPassageiros.getQtdPassageiros());
    }

    // obter total da taxa de embarque
    public BigDecimal getTaxaEmbarque(EspecificacaoPassageiros especificacaoPassageiros) {
        BigDecimal taxaEmbarqueIda = getVooIda().getTaxaEmbarque(especificacaoPassageiros);
        BigDecimal taxaEmbarqueVolta = getVooVolta().getTaxaEmbarque(especificacaoPassageiros);
        return taxaEmbarqueIda.add(taxaEmbarqueVolta);
    }

    // obter preco total sem taxas por tipo de passageiro
    public BigDecimal getPrecoSemTaxas(TipoPassageiro tipoPassageiro, Integer quantidade) {
        BigDecimal precoVooIda = getVooIda().getPreco(tipoPassageiro);
        BigDecimal precoVooVolta = getVooVolta().getPreco(tipoPassageiro);
        return precoVooIda.add(precoVooVolta).multiply(BigDecimal.valueOf(quantidade));
    }

    // obter total com taxas
    public BigDecimal getTotalRoteiro(EspecificacaoPassageiros especificacaoPassageiros) {

        List<TipoPassageiro> lista = especificacaoPassageiros.separadasPorTipo();

        return lista.stream()
                .map(tipo -> getPrecoSemTaxas(tipo, especificacaoPassageiros.getQtdPassageiros(tipo)))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    @Override
    public String toString() {
        return "Roteiro{" +
                "itinerariosIda[" + itinerariosIda.size() +"]=" + itinerariosIda +
                ", itinerariosVolta[" + itinerariosVolta.size() +"]=" + itinerariosVolta +
                ", taxaDeServico=" + taxaDeServico +
                '}';
    }

    private void adicionarItinerario(Itinerario itinerarioIda, Set<Itinerario> itinerarios) {
        boolean itinerarioNaLista = itinerarios.stream().anyMatch(itinerario -> itinerario.existeVoo(itinerarioIda.getPrimeiroVoo()));

        if (!itinerarioNaLista) {
            this.itinerariosIda.add(itinerarioIda);
        }
    }

    public Roteiro adicionarItinerarios(Itinerario itinerarioIda, Itinerario itinerarioVolta) {
        this.adicionarItinerario(itinerarioIda, getOpcoesIda());
        this.adicionarItinerario(itinerarioVolta, getOpcoesVolta());

        return this;
    }

    private BigDecimal getPrecoBase() {
        return this.getPrecoSemTaxas(TipoPassageiro.ADULTO, 1);
    }

    @Override
    public int compareTo(Roteiro roteiro) {
        return getPrecoBase().compareTo(roteiro.getPrecoBase());
    }
}

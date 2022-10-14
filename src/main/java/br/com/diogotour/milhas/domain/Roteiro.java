package br.com.diogotour.milhas.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Roteiro {

    Set<Itinerario> itinerariosIda = new HashSet<>();
    Set<Itinerario> itinerariosVolta = new HashSet<>();

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
    BigDecimal getTaxaServico(EspecificacaoPassageiros especificacaoPassageiros) {
        return taxaDeServico.multiply(especificacaoPassageiros.getQtdPassageiros());
    }

    // obter total da taxa de embarque
    BigDecimal getTaxaEmbarque(EspecificacaoPassageiros especificacaoPassageiros) {
        BigDecimal taxaEmbarqueIda = getVooIda().getTaxaEmbarque(especificacaoPassageiros);
        BigDecimal taxaEmbarqueVolta = getVooVolta().getTaxaEmbarque(especificacaoPassageiros);
        return taxaEmbarqueIda.add(taxaEmbarqueVolta);
    }

    // obter preco total sem taxas por tipo de passageiro
    BigDecimal getPrecoSemTaxas(TipoPassageiro tipoPassageiro, Integer quantidade) {
        BigDecimal precoVooIda = getVooIda().getPreco(tipoPassageiro);
        BigDecimal precoVooVolta = getVooVolta().getPreco(tipoPassageiro);
        return precoVooIda.add(precoVooVolta).multiply(BigDecimal.valueOf(quantidade));
    }

    // obter total com taxas
    BigDecimal getTotalRoteiro(EspecificacaoPassageiros especificacaoPassageiros) {

        List<TipoPassageiro> lista = especificacaoPassageiros.separadasPorTipo();

        return lista.stream()
                .map(tipo -> getPrecoSemTaxas(tipo, especificacaoPassageiros.getQtdPassageiros(tipo)))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    @Override
    public String toString() {
        return "Roteiro{" +
                "itinerariosIda=" + itinerariosIda +
                ", itinerariosVolta=" + itinerariosVolta +
                ", taxaDeServico=" + taxaDeServico +
                '}';
    }
}

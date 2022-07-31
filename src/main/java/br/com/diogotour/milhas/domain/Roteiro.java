package br.com.diogotour.milhas.domain;

import java.math.BigDecimal;
import java.util.List;

public class Roteiro {

    List<Itinerario> itinerariosIda;
    List<Itinerario> itinerariosVolta;

    BigDecimal taxaDeServico;

    List<Itinerario> getOpcoesIda() {
        return itinerariosIda;
    }

    public List<Itinerario> getOpcoesVolta() {
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
}

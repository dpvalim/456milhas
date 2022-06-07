package br.com.diogotour.milhas;

import java.math.BigDecimal;
import java.util.List;

public class Roteiro {

    List<Voo> voosIda;
    List<Voo> voosVolta;

    BigDecimal taxaDeServico;

    List<Voo> getVoosIda() {
        return voosIda;
    }

    public List<Voo> getVoosVolta() {
        return voosVolta;
    }

    private Voo getVooVolta() {
        return getVoosVolta().stream().findFirst().get();
    }

    private Voo getVooIda() {
        return getVoosIda().stream().findFirst().get();
    }

    // obter total de taxa de servico por quantidade de passageiros
    BigDecimal getTaxaServico(EspecificacaoPassageiros especificacaoPassageiros) {
        BigDecimal qtdPassageiros = BigDecimal.valueOf(especificacaoPassageiros.getQtdPassageiros());
        return taxaDeServico.multiply(qtdPassageiros);
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

        BigDecimal total = BigDecimal.ZERO;

        List<TipoPassageiro> lista = especificacaoPassageiros.separadasPorTipo();
        for (TipoPassageiro tipo : lista) {
            total = total.add(getPrecoSemTaxas(tipo, especificacaoPassageiros.getQtdPassageiros(tipo)));
        }

        total = total.add(getTaxaServico(especificacaoPassageiros)).add(getTaxaEmbarque(especificacaoPassageiros));
        return total;
    }
}

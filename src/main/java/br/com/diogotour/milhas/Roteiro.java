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

    // obter total de taxa de servico
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
    BigDecimal getPreco(TipoPassageiro tipoPassageiro) {
        BigDecimal precoVooIda = getVooIda().getPreco(tipoPassageiro);
        BigDecimal precoVooVolta = getVooVolta().getPreco(tipoPassageiro);
        return precoVooIda.add(precoVooVolta);
    }
}

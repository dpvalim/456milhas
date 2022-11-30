package br.com.diogotour.milhas.domain;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class EspecificacaoPassageiros {

    private Map<TipoPassageiro, Integer> especificacao;

    EspecificacaoPassageiros(Map<TipoPassageiro, Integer> especificacao) {
        this.especificacao = especificacao;
    }

    public BigDecimal getQtdPassageiros() {
        return BigDecimal.valueOf(especificacao.values().stream().reduce(0, Integer::sum));
    }

    public List<TipoPassageiro> separadasPorTipo() {
        return new ArrayList<>(especificacao.keySet());
    }

    public Integer getQtdPassageiros(TipoPassageiro...tipos) {
        return Arrays.stream(Optional.ofNullable(tipos).orElse(TipoPassageiro.values()))
                .mapToInt(tipo -> especificacao.getOrDefault(tipo, 0)).sum();
    }

    public static class EspecificacaoPassageirosBuilder {
        private Map<TipoPassageiro, Integer> especificacao;

        public EspecificacaoPassageirosBuilder(TipoPassageiro tipo, Integer quantidade) {
            this.especificacao = new HashMap<>();
            this.com(tipo, quantidade);
        }

        public EspecificacaoPassageirosBuilder com(TipoPassageiro tipo, Integer quantidade) {
            this.especificacao.putIfAbsent(tipo, quantidade);
            return this;
        }

        public EspecificacaoPassageiros build() {
            return new EspecificacaoPassageiros(especificacao);
        }
    }

}

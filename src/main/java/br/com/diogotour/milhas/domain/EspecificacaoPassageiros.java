package br.com.diogotour.milhas.domain;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class EspecificacaoPassageiros {

    protected Map<TipoPassageiro, Integer> especificacao = new HashMap<>();

    public BigDecimal getQtdPassageiros() {
        return BigDecimal.valueOf(especificacao.values().stream().reduce(0, Integer::sum));
    }

    public List<TipoPassageiro> separadasPorTipo() {
        return new ArrayList<>(especificacao.keySet());
    }

    public Integer getQtdPassageiros(TipoPassageiro...tipos) {
        return Arrays.stream(tipos == null || tipos.length == 0 ? TipoPassageiro.values() : tipos).mapToInt(tipo -> especificacao.getOrDefault(tipo, 0)).sum();
    }
}

package br.com.diogotour.milhas.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EspecificacaoPassageiros {

    Map<TipoPassageiro, Integer> especificacao = new HashMap<>();

    public BigDecimal getQtdPassageiros() {
        return BigDecimal.valueOf(especificacao.values().stream().reduce(0, Integer::sum));
    }

    public List<TipoPassageiro> separadasPorTipo() {
        return new ArrayList<>(especificacao.keySet());
    }

    public Integer getQtdPassageiros(TipoPassageiro tipo) {
        return especificacao.get(tipo);
    }
}

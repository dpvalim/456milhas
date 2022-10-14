package br.com.diogotour.milhas.domain;

import br.com.diogotour.milhas.repository.VooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static br.com.diogotour.milhas.domain.TipoPassageiro.*;

@Service
public class TodosRoteiros {

    @Autowired
    VooRepository repository;

    public List<Roteiro> com(String origem, String destino,
                      LocalDateTime dataHoraIda, LocalDateTime dataHoraVolta,
                      EspecificacaoPassageiros passageiros) {

        List<Voo> voosIda = repository.buscarUsandoFiltros(dataHoraIda, origem, destino,
                passageiros.getQtdPassageiros(ADULTO, CRIANCA),
                passageiros.getQtdPassageiros(BEBE));

        List<Voo> voosVolta = repository.buscarUsandoFiltros(dataHoraVolta, destino, origem,
                passageiros.getQtdPassageiros(ADULTO, CRIANCA),
                passageiros.getQtdPassageiros(BEBE));

        Map<BigDecimal, Roteiro> opcoesRoteiros = new HashMap();
        for (final Voo voo : voosIda) {
            for (final Voo volta : voosVolta) {
                final BigDecimal total = voo.preco.add(volta.preco);
                final Roteiro roteiro;

                final Itinerario itinerarioIda = new Itinerario(voo);
                final Itinerario itinerarioVolta = new Itinerario(volta);

                if (!opcoesRoteiros.containsKey(total)) {
                    roteiro = new Roteiro(itinerarioIda, itinerarioVolta);

                    opcoesRoteiros.put(total, roteiro);
                    continue;
                }

                roteiro = opcoesRoteiros.get(total);

                long itinerarioNaLista = roteiro.itinerariosIda.stream().filter(itinerario -> itinerario.existeVoo(voo)).count();

                if (itinerarioNaLista == 0) {
                    roteiro.itinerariosIda.add(itinerarioIda);
                }

                itinerarioNaLista = roteiro.itinerariosVolta.stream().filter(itinerario -> itinerario.existeVoo(volta)).count();
                if (itinerarioNaLista == 0) {
                    roteiro.itinerariosVolta.add(itinerarioVolta);
                }
            }
        }

        return opcoesRoteiros.values().stream().collect(Collectors.toList());
    }

}

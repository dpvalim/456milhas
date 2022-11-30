package br.com.diogotour.milhas.domain;

import br.com.diogotour.milhas.repository.VooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static br.com.diogotour.milhas.domain.TipoPassageiro.*;

@Service
public class TodosRoteiros {

    @Autowired
    VooRepository repository;

    public ListaDeRoteiros com(String origem, String destino,
                      LocalDateTime dataHoraIda, LocalDateTime dataHoraVolta,
                      EspecificacaoPassageiros passageiros) {

        Set<Voo> voosIda = repository.buscarUsandoFiltros(dataHoraIda, origem, destino,
                passageiros.getQtdPassageiros(ADULTO, CRIANCA),
                passageiros.getQtdPassageiros(BEBE));

        Set<Voo> voosVolta = repository.buscarUsandoFiltros(dataHoraVolta, destino, origem,
                passageiros.getQtdPassageiros(ADULTO, CRIANCA),
                passageiros.getQtdPassageiros(BEBE));

        ListaDeRoteiros roteiros = new ListaDeRoteiros(voosIda, voosVolta);

        return roteiros;
    }

}

package br.com.diogotour.milhas.domain;

import br.com.diogotour.milhas.repository.VooRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class TodosRoteirosTest {

    @Autowired
    TodosRoteiros todosRoteiros;

    @Test
    public void buscarVoos() {
        LocalDateTime dataHoraIda = LocalDateTime.of(2022, 10, 30, 0, 0);
        LocalDateTime dataHoraVolta = LocalDateTime.of(2022, 11, 1, 0, 0);
        EspecificacaoPassageiros passageiros = new EspecificacaoPassageiros();
        passageiros.especificacao = new HashMap<>();
        passageiros.especificacao.put(TipoPassageiro.ADULTO, 1);
//        passageiros.especificacao.put(TipoPassageiro.CRIANCA, 1);
//        passageiros.especificacao.put(TipoPassageiro.BEBE, 1);


        List<Roteiro> roteiros = todosRoteiros.com("SAO", "RIO", dataHoraIda, dataHoraVolta, passageiros);
        System.out.println("Qtd Roteiros: " + roteiros.size());
        roteiros.forEach(roteiro -> {
            System.out.println("\nValor Total Simulacao: " + roteiro.getPrecoSemTaxas(null, passageiros.getQtdPassageiros(null)));
            System.out.println("Qtd Opcoes Ida: " + roteiro.itinerariosIda.size());
            System.out.println("Qtd Opcoes Volta: " + roteiro.itinerariosVolta.size());
            System.out.println(roteiro);
        });
    }

}
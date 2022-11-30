package br.com.diogotour.milhas.domain;

import br.com.diogotour.milhas.domain.EspecificacaoPassageiros.EspecificacaoPassageirosBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static br.com.diogotour.milhas.domain.TipoPassageiro.*;

@SpringBootTest
public class TodosRoteirosTest {

    @Autowired
    TodosRoteiros todosRoteiros;

    @Test
    public void buscarVoos() {
        LocalDateTime dataHoraIda = LocalDateTime.of(2022, 10, 30, 0, 0);
        LocalDateTime dataHoraVolta = LocalDateTime.of(2022, 11, 1, 0, 0);

        EspecificacaoPassageiros passageiros = new EspecificacaoPassageirosBuilder(ADULTO, 1)
                .com(CRIANCA, 1)
                .com(BEBE, 1)
                .build();


        ListaDeRoteiros roteiros = todosRoteiros.com("SAO", "RIO", dataHoraIda, dataHoraVolta, passageiros);

        System.out.println("Qtd Roteiros: " + roteiros.size());
        roteiros.forEach(roteiro -> {
            System.out.println("\nValor Total Simulacao: " + roteiro.getPrecoSemTaxas(null, passageiros.getQtdPassageiros(null)));
            System.out.println("Qtd Opcoes Ida: " + roteiro.itinerariosIda.size());
            System.out.println("Qtd Opcoes Volta: " + roteiro.itinerariosVolta.size());
            System.out.println(roteiro);
        });
    }

}
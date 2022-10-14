package br.com.diogotour.milhas;

import br.com.diogotour.milhas.domain.Voo;
import br.com.diogotour.milhas.repository.VooRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ApplicationTests {

	@Autowired
	VooRepository repository;

	@Test
	void contextLoads() {
		LocalDateTime dataHora = LocalDateTime.of(2022, 10, 30, 0, 0);

		// voos de Congonhas para qualquer aeroporto do Rio
		List<Voo> voos = repository.buscarUsandoFiltros(dataHora, "CGH", "RIO", 1, 0);
		assertEquals(4, voos.size());

		// voos de Guarulhos para qualquer aeroporto do Rio
		voos = repository.buscarUsandoFiltros(dataHora, "GRU", "GIG", 3, 0);
		assertEquals(0, voos.size());

		// voos de Brasilia para Sao Luis
		voos = repository.buscarUsandoFiltros(dataHora, "BSB", "SLZ", 3, 0);
		assertEquals(1, voos.size());
	}

}

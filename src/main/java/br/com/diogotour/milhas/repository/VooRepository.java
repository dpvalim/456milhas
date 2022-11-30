package br.com.diogotour.milhas.repository;

import br.com.diogotour.milhas.domain.Localidade;
import br.com.diogotour.milhas.domain.Voo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface VooRepository extends JpaRepository<Voo, Long> {

    @Query("SELECT v FROM Voo v WHERE CAST(v.horaPartida AS date) = CAST(?1 AS date) " +
            "AND (v.localEmbarque.codigoIATA = ?2 OR v.localEmbarque.localidade.sigla = ?2) " +
            "AND (v.localDesembarque.codigoIATA = ?3 OR v.localDesembarque.localidade.sigla = ?3) " +
            "AND v.qtdAssentosDisponiveisAdulto >= ?4 AND v.qtdAssentosDisponiveisBebe >= ?5"
    )
    Set<Voo> buscarUsandoFiltros(LocalDateTime dataHora, String ida, String volta, Integer qtdAdultos, Integer qtdBebes);
}

package br.com.diogotour.milhas.domain;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toSet;

public class ListaDeRoteiros implements Iterable<Roteiro> {

    private Set<Roteiro> lista;

    public ListaDeRoteiros(Set<Voo> voosIda, Set<Voo> voosVolta) {
        lista = montarListaRoteiros(voosIda, voosVolta);
    }

    private SortedSet<Roteiro> montarListaRoteiros(Set<Voo> voosIda, Set<Voo> voosVolta) {
        Map<BigDecimal, Roteiro> opcoesRoteiros = new TreeMap<>();

        return getItinerarios(voosIda)
                .flatMap(itinerarioIda -> combinarItinerariosEmRoteiros(itinerarioIda, getItinerarios(voosVolta), opcoesRoteiros)
                ).collect(toCollection(TreeSet::new));
    }

    private Stream<Roteiro> combinarItinerariosEmRoteiros(Itinerario itinerarioIda, Stream<Itinerario> itinerariosVolta, Map<BigDecimal, Roteiro> opcoesRoteiros) {
        return itinerariosVolta.map(itinerarioVolta -> mergeRoteiro(opcoesRoteiros, itinerarioIda, itinerarioVolta));
    }

    private Stream<Itinerario> getItinerarios(Set<Voo> voos) {
        return voos.stream().map(Itinerario::new).collect(toSet()).stream();
    }

    private Roteiro mergeRoteiro(Map<BigDecimal, Roteiro> opcoesRoteiros, Itinerario itinerarioIda, Itinerario itinerarioVolta) {
        return opcoesRoteiros.merge(itinerarioIda.somaPreco(itinerarioVolta),
                new Roteiro(itinerarioIda, itinerarioVolta),
                (roteiro1, roteiro2) -> roteiro1.adicionarItinerarios(itinerarioIda, itinerarioVolta));
    }

    @Override
    public Iterator<Roteiro> iterator() {
        return this.lista.iterator();
    }

    public int size() {
        return this.lista.size();
    }
}

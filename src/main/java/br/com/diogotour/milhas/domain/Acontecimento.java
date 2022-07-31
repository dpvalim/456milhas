package br.com.diogotour.milhas.domain;

import java.time.Duration;
import java.time.LocalDateTime;

public abstract class Acontecimento implements Comparable<Acontecimento> {

    LocalDateTime horaPartida;
    LocalDateTime horaChegada;

    public LocalDateTime getHoraEmbarque() {
        return this.horaPartida;
    }

    public LocalDateTime getHoraDesembarque() {
        return this.horaChegada;
    }

    public Duration getDuracao() {
        return Duration.between(horaPartida, horaChegada);
    }

    public String getDuracaoFormatada() {
        return getDuracao().toHoursPart() + "h " + getDuracao().toMinutesPart() + "m";
    }

    @Override
    public int compareTo(Acontecimento o) {
        return this.horaPartida.compareTo(o.horaPartida);
    }

}

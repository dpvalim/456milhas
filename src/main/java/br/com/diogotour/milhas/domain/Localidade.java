package br.com.diogotour.milhas.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Localidade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String sigla;
    String cidade;
    String estado;
    String pais;

    @OneToMany
    List<Aeroporto> aeroportos;

    public String getCidade() {
        return cidade;
    }
}

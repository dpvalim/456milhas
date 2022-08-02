package br.com.diogotour.milhas.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Localidade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String sigla;
    String cidade;
    String estado;
    String pais;

    public String getCidade() {
        return cidade;
    }
}

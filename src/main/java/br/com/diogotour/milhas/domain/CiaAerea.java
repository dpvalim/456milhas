package br.com.diogotour.milhas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CiaAerea {

    @Id
    @Column(length = 5)
    String codigo;
    String nome;

    public String getNome() {
        return this.nome;
    }
}

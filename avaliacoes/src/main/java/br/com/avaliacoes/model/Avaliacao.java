package br.com.avaliacoes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name="avaliacao")
public class Avaliacao {
    @Id
    private Long id;

    @Min(1) @Max(5)
    private int nota;
    private String descricao;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

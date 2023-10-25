package br.com.avaliacoes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name="avaliacao")
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(1) @Max(5)
    private int nota;
    private String descricao;

    private Long idProduto;

    public Avaliacao() {
    }

    public Avaliacao(Long id, int nota, String descricao, Long idProduto) {
        this.id = id;
        this.nota = nota;
        this.descricao = descricao;
        this.idProduto = idProduto;
    }

    public Avaliacao(int nota, String descricao, Long idProduto) {
        this.nota = nota;
        this.descricao = descricao;
        this.idProduto = idProduto;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getNota() {
        return nota;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getIdProduto() {
        return idProduto;
    }
}

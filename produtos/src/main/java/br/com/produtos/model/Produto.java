package br.com.produtos.model;

import br.com.avaliacoes.model.Avaliacao;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;


    public Produto() {
    }

    public Produto(String nome) {
        this.nome = nome;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

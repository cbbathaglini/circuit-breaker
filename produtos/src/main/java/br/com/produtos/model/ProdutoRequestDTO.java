package br.com.produtos.model;

import jakarta.validation.constraints.NotBlank;

public class ProdutoRequestDTO {

    @NotBlank
    private String nome;

    public Produto converter() {
        return new Produto(this.nome);
    }

    public String getNome() {
        return nome;
    }
}

package br.com.avaliacoes.model;

public class AvaliacaoRequestDTO {
    private int nota;
    private String descricao;

    private Long idProduto;


    public Avaliacao converter() {
        return new Avaliacao(this.nota, this.descricao, this.idProduto);
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

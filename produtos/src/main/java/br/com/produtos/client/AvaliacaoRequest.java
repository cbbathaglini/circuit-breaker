package br.com.produtos.client;

public class AvaliacaoRequest {
    private Long idProduto;

    public AvaliacaoRequest(Long idProduto) {
        this.idProduto = idProduto;
    }

    public Long getIdProduto() {
        return idProduto;
    }
}

package br.com.compras.model;

import br.com.produtos.model.Produto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Compra
{
    @Id
    private Long id;

    @OneToMany
    private List<Produto> listProdutos;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

package br.com.produtos.controller;

import br.com.produtos.model.Produto;
import br.com.produtos.model.ProdutoRequestDTO;
import br.com.produtos.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid ProdutoRequestDTO produtoRequestDTO){

        Produto produto = produtoRequestDTO.converter();
        produtoRepository.save(produto);

        return ResponseEntity.ok().build();
    }
}

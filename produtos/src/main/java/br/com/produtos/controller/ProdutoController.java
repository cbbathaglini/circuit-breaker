package br.com.produtos.controller;

import br.com.avaliacoes.model.Avaliacao;
import br.com.produtos.model.Produto;
import br.com.produtos.model.ProdutoRequestDTO;
import br.com.produtos.repository.ProdutoRepository;
import br.com.produtos.service.AvaliacoesDoProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private AvaliacoesDoProdutoService avaliacoesDoProdutoService;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid ProdutoRequestDTO produtoRequestDTO){

        Produto produto = produtoRequestDTO.converter();
        produtoRepository.save(produto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{idProduto}/avaliacoes")
    public ResponseEntity<?> getAllAvaliacoes(@PathVariable Long idProduto) throws Exception {

        List<Avaliacao> avaliacaoList = avaliacoesDoProdutoService.getAllAvaliacoes(idProduto);

        return ResponseEntity.ok().body(avaliacaoList);
    }

}

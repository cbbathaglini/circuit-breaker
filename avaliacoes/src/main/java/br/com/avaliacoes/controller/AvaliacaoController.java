package br.com.avaliacoes.controller;

import br.com.avaliacoes.model.Avaliacao;
import br.com.avaliacoes.model.AvaliacaoRequestDTO;
import br.com.avaliacoes.repository.AvaliacaoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;


    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid AvaliacaoRequestDTO avaliacaoRequestDTO){

        Avaliacao avaliacao = avaliacaoRequestDTO.converter();
        //TODO verificar se o id do produto realmente existe na base de dados
        avaliacaoRepository.save(avaliacao);

        return ResponseEntity.ok().build();
    }
    @GetMapping("/produtos/{idProduto}")
    public List<Avaliacao>  avaliacoesDoProduto(@PathVariable Long idProduto){

        return avaliacaoRepository.findAvaliacaoByProdutoId(idProduto);
    }
}

package br.com.avaliacoes.repository;

import br.com.avaliacoes.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao,Long> {
    @Query("SELECT a FROM Avaliacao a WHERE a.idProduto = :idProduto")
    List<Avaliacao> findAvaliacaoByProdutoId(Long idProduto);
}

package br.com.produtos.service;

import br.com.avaliacoes.model.Avaliacao;
import br.com.produtos.client.AvaliacaoRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
import java.util.stream.Collectors;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;


@Service
public class AvaliacoesDoProdutoService {

    private final Logger logger = LoggerFactory.getLogger(AvaliacoesDoProdutoService.class);
    private RestTemplate restTemplate;

    private final Map<Long, List<Avaliacao>> CACHE = new HashMap<>();
    //private final io.github.resilience4j.circuitbreaker.CircuitBreaker circuitBreaker;

    public AvaliacoesDoProdutoService() {
        this.restTemplate = new RestTemplateBuilder().build();
        //CircuitBreakerConfig config = CircuitBreakerConfig.ofDefaults();
        //circuitBreaker = io.github.resilience4j.circuitbreaker.CircuitBreaker.of("avaliacoesProdutoCB", config);
    }


    @CircuitBreaker(name="avaliacoesProdutoCB", fallbackMethod = "getAllAvaliacoesNoCache")
    public List<Avaliacao> getAllAvaliacoes(Long idProduto) throws Exception {
        List<Avaliacao> avaliacoesList = new ArrayList<>();
        try{
            AvaliacaoRequest avaliacaoRequest = new AvaliacaoRequest(idProduto);
            ResponseEntity<Object[]> response = restTemplate.exchange(
                    createUrlParameters("http://localhost:8089","/avaliacoes/produtos/", avaliacaoRequest.getIdProduto().toString()).toUriString(),
                    HttpMethod.GET,
                    new HttpEntity<>(createHeaders()),
                    Object[].class
                    );


            if(response.getStatusCode() != HttpStatus.OK){
                throw new Exception("status code: " + response.getStatusCode());
            }

            Object[] objects = response.getBody();

            for (Object o: objects) {
                LinkedHashMap obj = ((LinkedHashMap) o);
                avaliacoesList.add(new Avaliacao(
                                                convertIntegerToLongValue(obj.get("id")),
                                                (Integer) obj.get("nota"),
                                                (String) obj.get("descricao"),
                                                convertIntegerToLongValue(obj.get("idProduto"))));
            }


        }catch (Exception e){
            logger.error("[ERROR] Trying to connect with api avaliações: " + e);
            throw e;
        }

        logger.info("Alimentando o cache");
        CACHE.put(idProduto, avaliacoesList);
        return avaliacoesList;
    }

    public List<Avaliacao> getAllAvaliacoesNoCache(Long idProduto, Throwable t) {
        return CACHE.getOrDefault(idProduto, new ArrayList<>());
    }

    private Long convertIntegerToLongValue(Object id) {
        return Long.valueOf(((Integer) id).longValue());
    }

    private HttpHeaders createHeaders(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }

    private UriComponentsBuilder createUrlParameters(String url, String path, String id){
        return UriComponentsBuilder.fromHttpUrl(url+path+id);
    }
}

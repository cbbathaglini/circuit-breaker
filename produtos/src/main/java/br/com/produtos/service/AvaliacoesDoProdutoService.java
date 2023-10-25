package br.com.produtos.service;

import br.com.avaliacoes.model.Avaliacao;
import br.com.produtos.client.AvaliacaoRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvaliacoesDoProdutoService {
    private RestTemplate restTemplate;

    public AvaliacoesDoProdutoService(){
        this.restTemplate = new RestTemplateBuilder().build();
    }

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
            throw new Exception(e);
        }

        return avaliacoesList;
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

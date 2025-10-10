package com.consumindoAPICep.demo.Service;

import com.consumindoAPICep.demo.Entity.Cep;
import com.consumindoAPICep.demo.repository.CepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CepService {

    @Autowired
    private CepRepository cepRepository;

    public List<Cep> buscarCepPorRua(String uf, String localidade, String logradouro) {
        String url = UriComponentsBuilder
                .fromHttpUrl("http://viacep.com.br/ws")
                .pathSegment(uf, localidade, logradouro, "json")
                .build()
                .toUriString();

        RestTemplate restTemplate = new RestTemplate();
        // RestTemplate é uma classe do Spring usada para fazer requisições HTTP.

        ResponseEntity<Cep[]> response = restTemplate.getForEntity(url, Cep[].class);
        //getForEntity() chama a API e espera uma resposta em formato de array de Cep.


        List<Cep> resultados = Arrays.asList(response.getBody());
        // convertendo o array em lista e mostrando no corpo

        // Salvar os resultados no banco se ainda não estiverem salvos
        for (Cep dto : resultados) {
            if (!cepRepository.existsByCep(dto.getCep())) {
                Cep cep = new Cep();
                cep.setCep(dto.getCep());
                cep.setLogradouro(dto.getLogradouro());
                cep.setComplemento(dto.getComplemento());
                cep.setBairro(dto.getBairro());
                cep.setLocalidade(dto.getLocalidade());
                cep.setUf(dto.getUf());
                cep.setDdd(dto.getDdd());

                cepRepository.save(cep);

//                Percorre todos os resultados retornados pela API.
//
//                        Para cada resultado:
//
//                Verifica se o CEP já existe no banco.
//
//                Se não existe, cria um objeto Cep e copia os dados do CepResultDTO.
//
//                        Usa o cepRepository.save() para salvar no banco H2.
            }
        }

        return resultados;
    }

    public Cep consultaInformPorCep(String cep){
        RestTemplate restTemplate = new RestTemplate();

        String url = String.format("http://viacep.com.br/ws/%s/json", cep);
        ResponseEntity<Cep> response = restTemplate.getForEntity(url, Cep.class);

        return response.getBody();
    }
}

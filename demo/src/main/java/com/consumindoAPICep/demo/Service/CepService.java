package com.consumindoAPICep.demo.service;

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

    public List<Cep> buscarCep(String uf, String localidade, String logradouro) {
        String url = UriComponentsBuilder
                .fromHttpUrl("http://viacep.com.br/ws")
                .pathSegment(uf, localidade, logradouro, "json")
                .build()
                .toUriString();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Cep[]> response = restTemplate.getForEntity(url, Cep[].class);

        List<Cep> resultados = Arrays.asList(response.getBody());

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
            }
        }

        return resultados;
    }
}

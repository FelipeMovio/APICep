package com.consumindoAPICep.demo.Service;

import com.consumindoAPICep.demo.Entity.Cep;
import com.consumindoAPICep.demo.repository.CepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CepService {

    @Autowired
    private CepRepository cepRepository;

    @Cacheable(value = "buscaPorRua", key = "#uf + '-' + #localidade + '-' + #logradouro")
    public List<Cep> buscarCepPorRua(String uf, String localidade, String logradouro) {
        System.out.println("🔍 Consultando API externa para a buscarCepPorRua: " + uf + localidade + logradouro);

        String url = UriComponentsBuilder
                .fromHttpUrl("http://viacep.com.br/ws")
                .pathSegment(uf, localidade, logradouro, "json")
                .build()
                .toUriString();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Cep[]> response = restTemplate.getForEntity(url, Cep[].class);

        List<Cep> resultados = Arrays.asList(response.getBody());

        List<Cep> salvos = new ArrayList<>();

        for (Cep dto : resultados) {
            Optional<Cep> existente = cepRepository.findByCep(dto.getCep());

            if (existente.isPresent()) {
                Cep cepExistente = existente.get();
                cepExistente.setQtdPesquisas(cepExistente.getQtdPesquisas() + 1);
                cepExistente = cepRepository.save(cepExistente);
                salvos.add(cepExistente);
            } else {
                Cep novoCep = new Cep();
                novoCep.setCep(dto.getCep());
                novoCep.setLogradouro(dto.getLogradouro());
                novoCep.setComplemento(dto.getComplemento());
                novoCep.setBairro(dto.getBairro());
                novoCep.setLocalidade(dto.getLocalidade());
                novoCep.setUf(dto.getUf());
                novoCep.setDdd(dto.getDdd());
                novoCep.setQtdPesquisas(1); // primeira vez

                novoCep = cepRepository.save(novoCep);
                salvos.add(novoCep);
            }
        }

        return salvos;
    }

    @Cacheable(value = "ceps" , key = "#cep")
    public Cep consultaInformPorCep(String cep) {
        System.out.println("🔍 Consultando API externa para o CEP: " + cep);

        Optional<Cep> existente = cepRepository.findByCep(cep);

        if (existente.isPresent()) {
            Cep encontrado = existente.get();
            encontrado.setQtdPesquisas(encontrado.getQtdPesquisas() + 1);
            return cepRepository.save(encontrado);
        }

        // Se não tiver no banco, busca na API
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("http://viacep.com.br/ws/%s/json", cep);
        ResponseEntity<Cep> response = restTemplate.getForEntity(url, Cep.class);

        Cep novo = response.getBody();
        novo.setQtdPesquisas(1); // primeira vez
        return cepRepository.save(novo);
    }

}

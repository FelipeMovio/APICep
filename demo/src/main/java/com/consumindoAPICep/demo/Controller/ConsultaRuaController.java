package com.consumindoAPICep.demo.Controller;


import com.consumindoAPICep.demo.dto.CepResultDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/consult-rua")
public class ConsultaRuaController {

    @GetMapping("{uf}/{localidade}/{logradouro}")
    public List<CepResultDTO> consultaRua(@PathVariable("uf") String uf,
                                          @PathVariable("localidade") String localidade,
                                          @PathVariable("logradouro") String logradouro) {
        RestTemplate restTemplate = new RestTemplate(); // Criação do bjeto para trabalhar com restTemplate
        ResponseEntity<CepResultDTO[]> resp = restTemplate.getForEntity(
                String.format("http://viacep.com.br/ws/%s/%s/%s/json", uf, localidade, logradouro),
                CepResultDTO[].class
        );

        return Arrays.asList(resp.getBody()); // converte array em lista
    }
}

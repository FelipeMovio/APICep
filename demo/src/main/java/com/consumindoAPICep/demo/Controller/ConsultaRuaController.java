package com.consumindoAPICep.demo.Controller;

import com.consumindoAPICep.demo.dto.CepResultDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/consult-rua")
public class ConsultaRuaController {

    @GetMapping("/{uf}/{localidade}/{logradouro}")
    public List<CepResultDTO> consultaRua(@PathVariable String uf,
                                          @PathVariable String localidade,
                                          @PathVariable String logradouro) {
        try {
            String url = UriComponentsBuilder
                    .fromHttpUrl("http://viacep.com.br/ws")
                    .pathSegment(uf, localidade, logradouro, "json")
                    .build()
                    .toUriString();

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<CepResultDTO[]> resp = restTemplate.getForEntity(url, CepResultDTO[].class);

            return Arrays.asList(resp.getBody());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao consultar API ViaCEP: " + e.getMessage());
        }
    }
}

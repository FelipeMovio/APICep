package com.consumindoAPICep.demo.Controller;

import com.consumindoAPICep.demo.dto.CepResultDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/consult-cep")
public class ConsultaCepController {

    @GetMapping("/{cep}")
    public CepResultDTO consultaCep (@PathVariable("cep") String cep){
        RestTemplate restTemplate = new RestTemplate(); // Criação do bjeto para trabalhar com restTemplate
        ResponseEntity<CepResultDTO> resp =
                restTemplate
                        .getForEntity(
                                String.format("http://viacep.com.br/ws/%s/json", cep),
                                CepResultDTO.class);
        return resp.getBody();
    }
}

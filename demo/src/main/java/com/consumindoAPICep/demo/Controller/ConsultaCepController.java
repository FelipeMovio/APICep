package com.consumindoAPICep.demo.Controller;

import com.consumindoAPICep.demo.Entity.Cep;
import com.consumindoAPICep.demo.Service.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/consult-cep")
public class ConsultaCepController {

    @Autowired
    private CepService cepService;

    @GetMapping("/{cep}")
    public Cep consultCep (@PathVariable("cep") String cep) {
        return cepService.consultaInformPorCep(cep);
    }
}

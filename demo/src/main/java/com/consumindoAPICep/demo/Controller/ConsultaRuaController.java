package com.consumindoAPICep.demo.Controller;

import com.consumindoAPICep.demo.Entity.Cep;
import com.consumindoAPICep.demo.Service.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/consult-rua")
public class ConsultaRuaController {

    @Autowired
    private CepService cepService;

    @GetMapping("/{uf}/{localidade}/{logradouro}")
    public List<Cep> consultaRua(@PathVariable String uf,
                                 @PathVariable String localidade,
                                 @PathVariable String logradouro) {
        return cepService.buscarCepPorRua(uf, localidade, logradouro);
    }
}

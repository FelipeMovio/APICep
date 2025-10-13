package com.consumindoAPICep.demo.Controller;

import com.consumindoAPICep.demo.Entity.Cep;
import com.consumindoAPICep.demo.repository.CepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resultados")
public class MaisPesquisadosController {

    @Autowired
    private CepRepository cepRepository;

    @GetMapping("/mais-pesquisados")
    public List<Cep> maisPesquisados() {
        return cepRepository.findTop10ByOrderByQtdPesquisasDesc();
    }
}

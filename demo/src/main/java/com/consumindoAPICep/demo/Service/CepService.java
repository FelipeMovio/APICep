package com.consumindoAPICep.demo.Service;

import com.consumindoAPICep.demo.repository.CepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CepService {

    @Autowired
    private CepRepository cepRepository;


}

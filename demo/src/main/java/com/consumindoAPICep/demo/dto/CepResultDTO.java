package com.consumindoAPICep.demo.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public record CepResultDTO(
        // format de record para ser imultavel
        // espelho do retorno que a viaCep(API que esta sendo consumida) nos tras
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id,

        String cep,
        String logradouro,
        String complemento,
        String unidade,
        String bairro,
        String localidade,
        String uf,
        String estado,
        String regiao,
        String ibge,
        String gia,
        String ddd,
        String siafi
        ) { }

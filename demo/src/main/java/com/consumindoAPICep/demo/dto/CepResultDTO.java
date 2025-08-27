package com.consumindoAPICep.demo.dto;

public record CepResultDTO(
        String cep,
        String loradouro,
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

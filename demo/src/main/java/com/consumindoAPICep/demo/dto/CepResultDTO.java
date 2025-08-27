package com.consumindoAPICep.demo.dto;

public record CepResultDTO(
        // format de record para ser imultavel
        // espelho do retorno que a viaCep(API que esta sendo consumida) nos tras
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

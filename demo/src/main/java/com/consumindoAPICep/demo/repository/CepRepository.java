package com.consumindoAPICep.demo.repository;

import com.consumindoAPICep.demo.Entity.CepResultDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CepRepository extends JpaRepository<CepResultDTO,Long> {
    boolean existsByCep(String cep);
}

package com.consumindoAPICep.demo.repository;


import com.consumindoAPICep.demo.Entity.Cep;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CepRepository extends JpaRepository<Cep,Long> {
    boolean existsByCep(String cep);
}

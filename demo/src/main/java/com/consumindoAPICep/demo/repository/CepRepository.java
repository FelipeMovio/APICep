package com.consumindoAPICep.demo.repository;


import com.consumindoAPICep.demo.Entity.Cep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CepRepository extends JpaRepository<Cep,Long> {
    boolean existsByCep(String cep);
    Optional<Cep> findByCep(String cep);
    List<Cep> findTop10ByOrderByQtdPesquisasDesc();


}

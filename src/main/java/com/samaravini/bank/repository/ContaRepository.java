package com.samaravini.bank.repository;

import com.samaravini.bank.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository <Conta, Long> {

    Optional<Conta> findByCpf(String cpf);
}

package com.samaravini.bank.model.dtos;

import com.samaravini.bank.model.Conta;
import com.samaravini.bank.model.Status;
import com.samaravini.bank.model.TipoConta;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContaResponseDTO(
        String numero,
        String nome,
        String cpf,
        LocalDate dataAbertura,
        BigDecimal saldo,
        TipoConta tipo,
        Status status
) {
    public ContaResponseDTO(Conta conta) {
        this(conta.getNumero(), conta.getNome(), conta.getCpf(),conta.getDataAbertura(),conta.getSaldo(),conta.getTipo(), conta.getStatus());
    }
}

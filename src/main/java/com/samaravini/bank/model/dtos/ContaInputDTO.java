package com.samaravini.bank.model.dtos;

import com.samaravini.bank.model.Status;
import com.samaravini.bank.model.TipoConta;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContaInputDTO(
        @NotBlank String numero,

        @NotBlank String nome,

        @CPF String cpf,

        @PastOrPresent LocalDate dataAbertura,

        @Positive BigDecimal saldo,

        @NotNull TipoConta tipo,

        @NotNull Status status
) {
}

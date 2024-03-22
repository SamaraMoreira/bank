package com.samaravini.bank.model.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record DadosDepositoDTO (
        @NotNull Long id,
        @Positive BigDecimal valor
        ){
}

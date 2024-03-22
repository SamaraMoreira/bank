package com.samaravini.bank.model;

import com.samaravini.bank.model.dtos.ContaInputDTO;
import com.samaravini.bank.model.dtos.DadosDepositoDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "contas")
@Data
@NoArgsConstructor
public class Conta {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String numero;

    private String nome;

    @Column(unique = true)
    private String cpf;

    private LocalDate dataAbertura;

    private BigDecimal saldo;

    @Enumerated(EnumType.STRING)
    private TipoConta tipo;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Conta(ContaInputDTO inputConta) {
        this.numero = inputConta.numero();
        this.nome = inputConta.nome();
        this.cpf = inputConta.cpf();
        this.dataAbertura = inputConta.dataAbertura();
        this.saldo = inputConta.saldo();
        this.tipo = inputConta.tipo();
        this.status = inputConta.status();
    }

    public void remove() {
        this.status = Status.INATIVO;
    }

    public void depositar(DadosDepositoDTO dados) {
        this.saldo = this.saldo.add(dados.valor());
    }

    public void saque(DadosDepositoDTO dados) {
        this.saldo = this.saldo.subtract(dados.valor());
    }
}

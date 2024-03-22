package com.samaravini.bank.controllers;

import com.samaravini.bank.model.Conta;
import com.samaravini.bank.model.dtos.ContaInputDTO;
import com.samaravini.bank.model.dtos.ContaResponseDTO;
import com.samaravini.bank.model.dtos.DadosDepositoDTO;
import com.samaravini.bank.repository.ContaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private ContaRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<ContaResponseDTO> get(@PathVariable Long id){
        var conta = repository.findById(id);
        if(conta.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new ContaResponseDTO(conta.get()));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ContaResponseDTO> getByCpf (@PathVariable String cpf){
        var conta = repository.findByCpf(cpf);
        if(conta.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new ContaResponseDTO(conta.get()));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid ContaInputDTO inputConta){
        Conta conta = new Conta(inputConta);
        Conta contaSaved = repository.save(conta);
        return ResponseEntity.created(buildUri(contaSaved)).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        var conta = repository.findById(id);
        if (conta.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        conta.get().remove();
        repository.save(conta.get());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/deposito")
    @Transactional
    public ResponseEntity<ContaResponseDTO> depositar(@RequestBody @Valid DadosDepositoDTO dados){
        var conta = repository.findById(dados.id());
        if (conta.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        conta.get().depositar(dados);
        repository.save(conta.get());
        return ResponseEntity.ok(new ContaResponseDTO(conta.get()));
    }

    @PostMapping("/saque")
    @Transactional
    public ResponseEntity saque(@RequestBody @Valid DadosDepositoDTO dados){
        var conta = repository.findById(dados.id());
        if (conta.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        if (conta.get().getSaldo().compareTo(dados.valor()) < 0) {
            return ResponseEntity.badRequest().body("Saldo insuficiente");
        }
        conta.get().saque(dados);
        repository.save(conta.get());
        return ResponseEntity.ok(new ContaResponseDTO(conta.get()));
    }

    private URI buildUri(Conta conta){
        var path = "/"+conta.getId();
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(path)
                .build()
                .toUri();
    }





}

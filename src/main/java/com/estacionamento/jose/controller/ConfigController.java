package com.estacionamento.jose.controller;

import com.estacionamento.jose.entity.Conductor;
import com.estacionamento.jose.entity.Configuracao;
import com.estacionamento.jose.entity.Model;
import com.estacionamento.jose.repository.ConductorRepository;
import com.estacionamento.jose.repository.ConfiguracaoRepository;
import com.estacionamento.jose.service.ConductorService;
import com.estacionamento.jose.service.ConfiguracaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/configuracao")
public class ConfigController {

    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

    @Autowired
    private ConfiguracaoService configuracaoService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdRequest(@PathVariable("id") final Long id){

        final Configuracao configuracao = this.configuracaoRepository.findById(id).orElse(null);
        return configuracao == null
                ? ResponseEntity.badRequest().body("Nenhum valor Encontrado")
                : ResponseEntity.ok(configuracao);
    }

    @PostMapping
    public ResponseEntity<?> configuracao(@RequestBody final Configuracao configuracao){
        try{
            this.configuracaoRepository.save(configuracao);
            return ResponseEntity.ok("REGISTRO CADASTRADO COM SUCESSO");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("erro" +e.getStackTrace());
        }
    }

    @PutMapping
    public ResponseEntity<?> edit(@RequestParam("id") final Long id, @RequestBody final Configuracao configuracao) {

        try {
            final Configuracao configuracaoBanco = this.configuracaoRepository.findById(id).orElse(null);

            if (configuracaoBanco == null || configuracaoBanco.getId() != configuracao.getId()){
                throw new RuntimeException("O registro nao foi encontrado");
            }

            this.configuracaoRepository.save(configuracao);
            return ResponseEntity.ok("registro cadastrado");

        }catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("erro" + e.getCause().getCause().getMessage());
        }catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("erro" + e.getMessage());
        }
    }
    }


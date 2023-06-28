package com.estacionamento.jose.controller;

import com.estacionamento.jose.entity.Movement;
import com.estacionamento.jose.repository.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/relatorio")
@CrossOrigin("http://localhost:8081")
public class GerarRelatorio {

    @Autowired
    private final MovementRepository movimentacaoRepository;

    @Autowired
    public GerarRelatorio(MovementRepository movimentacaoRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
    }

    @GetMapping("/gerar")
    public ResponseEntity<String> gerarRelatorio() {
        try {
            Movement movimentacao = movimentacaoRepository.findAll().get(0);
            return new ResponseEntity<>(movimentacao.gerarRelatorio(), HttpStatus.OK);
        } catch (Exception e) {

            e.printStackTrace();

            return new ResponseEntity<>("Erro ao gerar relatório", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
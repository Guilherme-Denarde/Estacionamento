package com.estacionamento.jose.service;

import com.estacionamento.jose.controller.MovementController;
import com.estacionamento.jose.entity.Conductor;
import com.estacionamento.jose.entity.Configuracao;
import com.estacionamento.jose.entity.Movement;
import com.estacionamento.jose.repository.ConductorRepository;
import com.estacionamento.jose.repository.ConfiguracaoRepository;
import com.estacionamento.jose.repository.MovementRepository;
//import com.estacionamento.jose.repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class MovementService {
    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

    @Autowired
    private ConductorRepository conductorRepository;

    @Autowired
    private ConfiguracaoService configuracaoService;


    public ResponseEntity<String> sair(final Long id) {
        Movement movimentacao = movementRepository.findById(id).orElse(null);

        if (movimentacao != null) {
            BigDecimal valorHora = configuracaoService.getValorHora();
            movimentacao.setValorHora(valorHora);
            movimentacao.setExit(LocalDateTime.now());
            movimentacao.calcularTempoUtilizado();
            movimentacao.calcularValorTotal();

            String relatorio = movimentacao.gerarRelatorio();

            System.out.println(relatorio);

            movementRepository.save(movimentacao);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("erro na saida");
        }
        return ResponseEntity.ok().body("Deu certo");
    }

    @Transactional(rollbackFor = Exception.class)
    public void signup(final Movement movement){

        Assert.isTrue(movement.getVehicleId() != null, "Veiculo não encontrado");

        Assert.isTrue(movement.getConductorId() != null, "Condutor não encontrado");

        Assert.isTrue(movement.getEnter() != null, "Entrada está invalida");

        this.movementRepository.save(movement);
    }

    @Transactional(rollbackFor = Exception.class)
    public void edited(final Movement movement, final Long id){
        final Movement movementBanco = this.movementRepository.findById(id).orElse(null);

        Assert.isTrue(movementBanco != null || this.movementRepository.findById(id).equals(movement.getId()), "Não foi possivel identificar o registro informado.");

        Assert.isTrue(movement.getVehicleId() != null, "Veiculo não encontrado");

        Assert.isTrue(movement.getConductorId() != null, "Condutor não encontrado");

        Assert.isTrue(movement.getEnter() != null, "Entrada está invalida");

        this.movementRepository.save(movement);
    }

//    @Transactional
//    public void horaFinal(final Long id) {
//
//        final Movement moviBanco = this.movementRepository.findById(id).orElse(null);
//        Assert.isTrue(moviBanco != null, "movimentacao nao encontrada");
//
//        final LocalDateTime exit = LocalDateTime.now();
//        Duration duracaoHora = Duration.between(moviBanco.getEnter(), exit);
//
//        final Configuracao configuracao = this.configuracaoRepository.findById(1L).orElse(null);
//        Assert.isTrue(configuracao != null, "configuracao nao existe");
//
//        final Conductor conductor = this.conductorRepository.findById(moviBanco.getConductorId().getId()).orElse(null);
//        Assert.isTrue(conductor != null, "condutor nao existe");
//
//        //Set no tempo pra receber da duração entre saida e entrada a parte dos minutos e horas
//        moviBanco.setTempoMinuto(duracaoHora.toMinutesPart());
//        moviBanco.setTempoHora(duracaoHora.toHoursPart());
//        moviBanco.setExit(exit);
//
//        final BigDecimal hora = BigDecimal.valueOf(duracaoHora.toHoursPart());
//        final BigDecimal minuto = BigDecimal.valueOf(duracaoHora.toMinutesPart()).divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_EVEN);
//
//        BigDecimal precos = configuracao.getValorHora().multiply(hora).add(configuracao.getValorHora().multiply(minuto));
//
//        //mesma situação, cria um bigDecimal pra buscar o tempoDesconto q ele tem.
//        final BigDecimal tempoDesc = conductor.getTempoDesconto() != null ? conductor.getTempoDesconto() : new BigDecimal(0);
//
//        //e se caso um desconto existir,o condutor tem seu tempo de desconto adicionado.
//        if(configuracao.getGerarDesconto()){
//            conductor.setTempoDesconto(conductor.getTempoDesconto().add(hora.add(minuto)));
//        }
//        BigDecimal valor_desconto = BigDecimal.ZERO;
//
//        //verifica se ele precisa do desconto ou nao, um boolean
//        if(tempoDesc.compareTo(new BigDecimal(configuracao.getNecessarioDesconto())) >= 0){
//
//            //conta de valor do desconto, pega o valor da hora e multiplica pelo tempo de desconto
//            valor_desconto = configuracao.getValorHora().multiply(tempoDesc);
//
//            //set no movimentação para o valor do desconto, ou seja, precos - o valor
//            moviBanco.setValorDeconto(precos.subtract(valor_desconto));
//            conductor.setTempoDesconto(new BigDecimal(0));
//        }
//
//        //sets no banco, do preco com o valor de desconto
//        moviBanco.setValorTotal(precos.subtract(valor_desconto));
//        moviBanco.setValorHoraTotal(configuracao.getValorHora());
//        moviBanco.setValorHoraTotal(configuracao.getValorMinutoMulta());
//
//        this.conductorRepository.save(conductor);
//        this.movementRepository.save(moviBanco);
//
//
//    }
    @Transactional(rollbackFor = Exception.class)
    public void delete(final Movement movement){
        final Movement movementBanco = this.movementRepository.findById(movement.getId()).orElse(null);

        movementBanco.setActive(Boolean.FALSE);
        this.movementRepository.save(movement);
    }
}

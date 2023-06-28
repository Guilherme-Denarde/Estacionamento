package com.estacionamento.jose.service;

import com.estacionamento.jose.entity.Configuracao;
import com.estacionamento.jose.entity.Movement;
import com.estacionamento.jose.repository.ConfiguracaoRepository;
import com.estacionamento.jose.repository.MovementRepository;
//import com.estacionamento.jose.repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class MovementService {
    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

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

    @Transactional
    public void horaFinal(final Long id) {
//add the calculations for the relatorio here
    }
    @Transactional(rollbackFor = Exception.class)
    public void delete(final Movement movement){
        final Movement movementBanco = this.movementRepository.findById(movement.getId()).orElse(null);

        movementBanco.setActive(Boolean.FALSE);
        this.movementRepository.save(movement);
    }
}

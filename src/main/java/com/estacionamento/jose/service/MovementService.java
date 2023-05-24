package com.estacionamento.jose.service;

//import com.estacionamento.jose.Give;
import com.estacionamento.jose.entity.Movement;
import com.estacionamento.jose.entity.Setting;
import com.estacionamento.jose.repository.MovementRepository;
//import com.estacionamento.jose.repository.ConfigurationRepository;
import com.estacionamento.jose.repository.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Service
public class MovementService {
    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private SettingRepository settingRepository;

    @Transactional(rollbackFor = Exception.class)
    public void save(final Movement movement){

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

    @Transactional(rollbackFor = Exception.class)
    public void Exit(final Long id){

        Setting setting = settingRepository.findById(id).orElse(null);

        Movement movement = this.movementRepository.findById(id).orElse(null);

        System.out.println("Entrada: " + movement.getEnter());

        System.out.println("Saida: " + movement.getExit());

        Duration duration = Duration.between(movement.getEnter(),movement.getExit());

        System.out.println("Hora total: " + duration.toHours());

        System.out.println("Total a pagar: " + duration.toHours() * setting.getValueHour().longValue());

        this.movementRepository.save(movement);

    }


    @Transactional(rollbackFor = Exception.class)
    public void delete(final Movement movement){
        final Movement movementBanco = this.movementRepository.findById(movement.getId()).orElse(null);

        movementBanco.setActive(Boolean.FALSE);
        this.movementRepository.save(movement);
    }
}

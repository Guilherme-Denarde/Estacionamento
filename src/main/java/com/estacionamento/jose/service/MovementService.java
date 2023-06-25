package com.estacionamento.jose.service;

import com.estacionamento.jose.entity.Movement;
import com.estacionamento.jose.repository.MovementRepository;
//import com.estacionamento.jose.repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class MovementService {
    @Autowired
    private MovementRepository movementRepository;

//    @Autowired
//    private ConfigurationRepository configurationRepository;

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

//    @Transactional(rollbackFor = Exception.class)
//    public Give Exit(final Long id){
//
//        Movement movement = this.movementRepository.findById(id).orElse(null);
//
//        movement.setExit(LocalDateTime.now());
//
//        Long tempoTotal = movement.getEnter().until(movement.getExit(), ChronoUnit.HOURS);
//
//        movement.setTime(tempoTotal);
//
////        Configuration configuration = configurationRepository.findById().orElse(null);
//
////        Configuration configuration = (configuration) -> {
////            this.configurationRepository.findById().orElse(null)
////        };
//
//
//        BigDecimal hour = new BigDecimal(movement.getTime());
//
//        BigDecimal valueTotal = configuration.getValueHour().multiply(hour);
//
//        movement.setValueHour(valueTotal);
//
//        Long discount = movement.getTime() / configuration.getTempoParaDesconto();
//
//        movement.setDiscountValue(discount);
//
//        System.out.println(discount);
//
//        BigDecimal calc = new BigDecimal(discount).multiply(configuration.getTempoDeDesconto());
//
//        BigDecimal total = movement.getValueTotal().subtract(calc);
//
//        movement.setValueTotal(total);
//
//        this.movementRepository.save(movement);
//
//        return new Give(movement.getEnter(), movement.getExit(), movement.getConductorId(), movement.getVehicleId(), movement.getTime(), configuration.getTempoParaDesconto(), movement.getValueHour(), movement.getDiscountValue());
//    }


//
    @Transactional(rollbackFor = Exception.class)
    public void delete(final Movement movement){
        final Movement movementBanco = this.movementRepository.findById(movement.getId()).orElse(null);

        movementBanco.setActive(Boolean.FALSE);
        this.movementRepository.save(movement);
    }
}

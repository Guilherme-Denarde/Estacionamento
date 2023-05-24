package com.estacionamento.jose.service;

import com.estacionamento.jose.entity.Vehicle;
import com.estacionamento.jose.entity.Movement;
import com.estacionamento.jose.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Transactional(rollbackFor = Exception.class)
    public void signup(final Vehicle vehicle){

        Assert.isTrue(vehicle.getPlate().length() > 2, "Placa não encontrada");

        String regexPlacaOld = "^[A-Z]{3}-\\d{4}$";

        String regexPlatenew = "^[A-Z]{3}\\d{1}[A-Z]{1}\\d{2}$";

        Assert.isTrue(vehicle.getPlate().matches(regexPlacaOld) || vehicle.getPlate().matches(regexPlatenew), "Placa está errada");

        Assert.isTrue(vehicle.getModelId() != null, "Model não encontrado");

        Assert.isTrue(vehicle.getColor() != null, "Cor não encontrado");

        Assert.isTrue(vehicle.getType() != null, "type não encontrado");

        Assert.isTrue(vehicle.getYear() != null, "ano não encontrado");

        Assert.isTrue(this.vehicleRepository.findByPlate(vehicle.getPlate()).isEmpty(), "a placa ja existe");

        Long atual = 2024L;

        Assert.isTrue(vehicle.getYear() < atual, "Ano invalido para veiculo");

        this.vehicleRepository.save(vehicle);
    }

    @Transactional(rollbackFor = Exception.class)
    public void edit(final Vehicle vehicle, final Long id){
        final Vehicle vehicleBanco = this.vehicleRepository.findById(id).orElse(null);

        Assert.isTrue(vehicleBanco != null || vehicleBanco.getId() == vehicle.getId(), "Não foi possivel identificar o registro no banco");

        String regexPlacaOld = "^[A-Z]{3}-\\d{4}$";

        String regexPlacaNew = "^[A-Z]{3}\\d{1}[A-Z]{1}\\d{2}$";

        Assert.isTrue(vehicle.getPlate().length() > 2, "Placa não encontrada");

        Assert.isTrue(vehicle.getPlate().matches(regexPlacaOld) || vehicle.getPlate().matches(regexPlacaNew), "Placa está errada");

        Assert.isTrue(vehicle.getModelId() != null, "Modelo não encontrado");

        Assert.isTrue(vehicle.getColor() != null, "Cor não encontrado");

        Assert.isTrue(vehicle.getType() != null, "Tipo não encontrado");

        Assert.isTrue(vehicle.getYear() != null, "ano não encontrado");

        Assert.isTrue(this.vehicleRepository.findByPlatePut(vehicle.getPlate(), id).isEmpty(), "a placa ja existe");

        this.vehicleRepository.save(vehicle);
    }

    @Transactional(rollbackFor =  Exception.class)
    public void delete(final Vehicle vehicle){
        final Vehicle vehicleBanco = this.vehicleRepository.findById(vehicle.getId()).orElse(null);

        List<Movement> veiculoAtivo = this.vehicleRepository.findvehicleActiveMovimentacao(vehicleBanco);

        if(veiculoAtivo.isEmpty()){
            this.vehicleRepository.delete(vehicleBanco);
        } else{
            vehicleBanco.setActive(Boolean.FALSE);
            this.vehicleRepository.save(vehicle);
        }
    }
}

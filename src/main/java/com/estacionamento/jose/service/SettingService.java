package com.estacionamento.jose.service;

import com.estacionamento.jose.entity.Setting;
import com.estacionamento.jose.repository.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class SettingService {

    @Autowired
    private SettingRepository settingRepository;

    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(final Setting setting){

        Assert.isTrue(setting.getValueHour() != null, "Nao pode ser nulo");

        Assert.isTrue(setting.getValueTax() != null, "Nao pode ser nulo");

        Assert.isTrue(setting.getStartShift() != null, "Nao pode ser nulo");

        Assert.isTrue(setting.getEndShift() != null, "Nao pode ser nulo");

        Assert.isTrue(setting.getTimeDiscount() != null, "Nao pode ser nulo");

        Assert.isTrue(setting.getTimeForDiscount() != null, "Nao pode ser nulo");

        Assert.isTrue(setting.getGenerateDiscount() != null, "Nao pode ser nulo");

        Assert.isTrue(setting.getAvailBike() != null, "Nao pode ser nulo");

        Assert.isTrue(setting.getAvailCar() != null, "Nao pode ser nulo");

        Assert.isTrue(setting.getAvailVan() != null, "Nao pode ser nulo");

        this.settingRepository.save(setting);
    }

    @Transactional(rollbackFor = Exception.class)
    public void edit(final Setting setting, final Long id){
        final Setting settingBanco = this.settingRepository.findById(id).orElse(null);

        Assert.isTrue(setting.getValueHour() != null, "Nao pode ser nulo");

        Assert.isTrue(setting.getValueTax() != null, "Nao pode ser nulo");

        Assert.isTrue(setting.getStartShift() != null, "Nao pode ser nulo");

        Assert.isTrue(setting.getEndShift() != null, "Nao pode ser nulo");

        Assert.isTrue(setting.getTimeDiscount() != null, "Nao pode ser nulo");

        Assert.isTrue(setting.getTimeForDiscount() != null, "Nao pode ser nulo");

        Assert.isTrue(setting.getGenerateDiscount() != null, "Nao pode ser nulo");

        Assert.isTrue(setting.getAvailBike() != null, "Nao pode ser nulo");

        Assert.isTrue(setting.getAvailCar() != null, "Nao pode ser nulo");

        Assert.isTrue(setting.getAvailVan() != null, "Nao pode ser nulo");

        this.settingRepository.save(setting);

    }


}
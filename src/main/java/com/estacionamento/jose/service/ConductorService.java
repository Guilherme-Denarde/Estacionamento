package com.estacionamento.jose.service;

import com.estacionamento.jose.entity.Brand;
import com.estacionamento.jose.entity.Conductor;
import com.estacionamento.jose.repository.ConductorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class ConductorService {

    @Autowired
    private ConductorRepository conductorRepository;


    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(@RequestBody final Conductor conductor) {
        //NAME
        Assert.isTrue(conductor.getName().length() > 1, "O nome está vazio");
        Assert.isTrue(conductor.getName().matches("[a-zA-Z]+"), "O nome deve apenas conter letras");


        //CPF
        Assert.isTrue(conductor.getCpf().length() > 1, "Complete o campo CPF");
        Assert.isTrue(conductor.getCpf().matches("^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$"), "CPF invalido");
        Assert.isTrue(this.conductorRepository.findCpf(conductor.getCpf()).isEmpty(), "CPF existente");



        //PHONE NUMBER
        Assert.isTrue(conductor.getTelephone().length() > 1, "O campo telefone esta vazio");
        Assert.isTrue(conductor.getTelephone().matches("\\(\\d{2}\\)\\s?\\d{4,5}-\\d{4}"), "Numero de telefone invalido");
        Assert.isTrue(this.conductorRepository.findTelephone(conductor.getTelephone()).isEmpty(), "Telefone existente");

        this.conductorRepository.save(conductor);
    }

    @Transactional(rollbackFor = Exception.class)
    public void edit(final Conductor conductor, final Long id){

        final Conductor conductorBanco = this.conductorRepository.findById(id).orElse(null);

        Assert.isTrue(conductorBanco != null || conductorBanco.getId() == conductor.getId(), "Não foi possivel indenficar o registro no banco");

        Assert.isTrue(conductor.getName().length() > 1, "O nome está faltando");
        Assert.isTrue(conductor.getName().matches("[a-zA-Z]+"), "O nome deve apenas conter letras");

        this.conductorRepository.save(conductor);
    }


    @Transactional(rollbackFor = Exception.class)

    public void delete(final Conductor conductor){
        final Conductor conductorBanco = this.conductorRepository.findById(conductor.getId()).orElse(null);

        List<Conductor> conductorAtivo = this.conductorRepository.findByAtivo(conductor.isActive());

        if(conductorAtivo.isEmpty()){
            this.conductorRepository.delete(conductorBanco);
        } else{
            conductorBanco.setActive(Boolean.FALSE);
            this.conductorRepository.save(conductor);
        }
    }

}

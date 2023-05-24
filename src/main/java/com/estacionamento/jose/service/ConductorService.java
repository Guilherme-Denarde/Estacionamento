package com.estacionamento.jose.service;

import com.estacionamento.jose.entity.Brand;
import com.estacionamento.jose.entity.Conductor;
import com.estacionamento.jose.repository.ConductorRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void cadastrar(@RequestParam("name") final String name, @RequestBody final Conductor conductor){

        Assert.isTrue(conductor.getName() != null, "O nome está faltando");

        Assert.isTrue(!conductor.getName().matches("\b([A-ZÀ-ÿ][-,a-z. ']+[ ]*)"), "O nome deve conter apelido, nao deve ter numeros");

        this.conductorRepository.save(conductor);
    }

    @Transactional(rollbackFor = Exception.class)
    public void edit(final Conductor conductor, final Long id){

        final Conductor conductorBanco = this.conductorRepository.findById(id).orElse(null);

        Assert.isTrue(conductorBanco != null || conductorBanco.getId() == conductor.getId(), "Não foi possivel indenficar o registro no banco");

        Assert.isTrue(conductor.getName() != null, "O nome está faltando");

        Assert.isTrue(this.conductorRepository.findByNomePut(conductor.getName(),id).isEmpty(), "ja existe essa marca");

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

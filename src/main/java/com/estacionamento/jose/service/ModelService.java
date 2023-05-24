package com.estacionamento.jose.service;

import com.estacionamento.jose.entity.Model;
import com.estacionamento.jose.repository.ModelRepository;
import com.estacionamento.jose.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class ModelService {

        @Autowired
        private ModelRepository modelRepository;

    @Transactional(rollbackFor = Exception.class)
    public void signup(@RequestBody final Model model){

        Assert.isTrue(model.getName().length() > 2, "O nome está faltando");

        this.modelRepository.save(model);
    }

    @Transactional(rollbackFor = Exception.class)
    public void edited(final Model model, final Long id){

        final Model modelBanco = this.modelRepository.findById(id).orElse(null);

        Assert.isTrue(model.getName().length() > 2, "O nome está faltando");

        Assert.isTrue(this.modelRepository.findByNomePut(model.getName(), id).isEmpty(), "ja existe esse modelo");

        Assert.isTrue(modelBanco != null || modelBanco.getId() == model.getId(), "Não foi possivel indenficar o registro no banco");

        this.modelRepository.save(model);
    }

    @Transactional(rollbackFor = Exception.class)

    public void delete(final Model model){
        final Model modelBanco = this.modelRepository.findById(model.getId()).orElse(null);

        List<Model> modelActive = this.modelRepository.findByActiveModel(model.isActive());

        if(modelActive.isEmpty()){
            this.modelRepository.delete(modelBanco);
        } else{
            modelBanco.setActive(Boolean.FALSE);
            this.modelRepository.save(model);
        }
    }

  }

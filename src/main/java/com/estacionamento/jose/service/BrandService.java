package com.estacionamento.jose.service;

import com.estacionamento.jose.entity.Brand;
import com.estacionamento.jose.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(@RequestBody final Brand brand){


        Assert.isTrue(brand.getName().length() > 2, "O nome está faltando");

        this.brandRepository.save(brand);
    }

    @Transactional(rollbackFor = Exception.class)
    public void edit(final Brand brand, final Long id){

        final Brand brandBanco = this.brandRepository.findById(id).orElse(null);

        Assert.isTrue(brandBanco != null || this.brandRepository.findById(id).equals(brand.getId()), "Não foi possivel indenficar o registro no banco");

        Assert.isTrue(brand.getName().length() > 2, "O nome está faltando");

        Assert.isTrue(this.brandRepository.findByNomePut(brand.getName(),id).isEmpty(), "ja existe essa marca");

        this.brandRepository.save(brand);
    }


    @Transactional(rollbackFor = Exception.class)
    public void delete(final Brand brand){
        final Brand brandBanco = this.brandRepository.findById(brand.getId()).orElse(null);

        List<Brand> brandAtivo = this.brandRepository.findByActive(brand.isActive());

        if(brandAtivo.isEmpty()){
            this.brandRepository.delete(brandBanco);
        } else{
            brandBanco.setActive(Boolean.FALSE);
            this.brandRepository.save(brand);
        }
    }

}
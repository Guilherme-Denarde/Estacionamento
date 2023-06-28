package com.estacionamento.jose.repository;

import com.estacionamento.jose.entity.Configuracao;
import com.estacionamento.jose.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface ConfiguracaoRepository extends JpaRepository<Configuracao, Long> {

}


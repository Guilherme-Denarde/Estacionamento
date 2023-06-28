package com.estacionamento.jose;

import com.estacionamento.jose.entity.Conductor;
import com.estacionamento.jose.entity.Vehicle;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


public class Relatorio {

    @Getter
    @Setter
    private LocalDateTime entrada;
    @Getter
    @Setter
    private LocalDateTime saida;

    @Getter
    @Setter
    private Conductor conductor;
    @Getter
    @Setter
    private Vehicle vehicle;

    @Getter
    @Setter
    private Integer quantidadeHoras;
    @Getter
    @Setter
    private Integer quantidadeDesconto;

    @Getter
    @Setter
    private Integer valorPagar;
    @Getter
    @Setter
    private Integer valorDesconto;
}

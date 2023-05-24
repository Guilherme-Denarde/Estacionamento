package com.estacionamento.jose.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Table(name = "configuration", schema = "public")
public class Configuration extends AbstractEntity {

    @Getter
    @Setter
    @Column(name = "valor_hora")
    private BigDecimal valueHour;
    @Getter @Setter
    @Column(name = "valor_minuto_multa")
    private BigDecimal valorMinuteFine;
    @Getter @Setter
    @Column(name = "inicio_expediente")
    private LocalTime inicioExpediente;
    @Getter @Setter
    @Column(name = "fim_expediente")
    private LocalTime fimExpediente;
    @Getter @Setter
    @Column(name = "tempo_para_desconto")
    private Long tempoParaDesconto;
    @Getter @Setter
    @Column(name = "tempo_de_desconto")
    private BigDecimal tempoDeDesconto;
    @Getter @Setter
    @Column(name = "gerar_desconto")
    private Boolean gerarDesconto;
    @Getter @Setter
    @Column(name = "vagas_motos")
    private Integer vagasMotos;
    @Getter @Setter
    @Column(name = "vagas_carro")
    private Integer vagasCarro;
    @Getter @Setter
    @Column(name = "vagas_van")
    private Integer vagasVan;
}

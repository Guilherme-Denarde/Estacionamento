package com.estacionamento.jose.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Table(name = "settings", schema = "public")
public class Setting extends AbstractEntity {
    @Getter @Setter
    @Column(name = "valueHour", nullable = false)
    private BigDecimal valueHour;

    @Getter @Setter
    @Column(name = "valueTax", nullable = false)
    private BigDecimal valueTax;

    @Getter @Setter
    @Column(name = "startShift", nullable = false)
    private LocalTime startShift;

    @Getter @Setter
    @Column(name = "endShift", nullable = false)
    private LocalTime endShift;

    @Getter @Setter
    @Column(name = "timeForDiscount", nullable = false)
    private LocalTime timeForDiscount;

    @Getter @Setter
    @Column(name = "timeDiscount", nullable = false)
    private LocalTime timeDiscount;

    @Getter @Setter
    @Column(name = "generateDiscount", nullable = false)
    private Boolean generateDiscount;

    @Getter @Setter
    @Column(name = "availBike", nullable = false)
    private Integer availBike;

    @Getter @Setter
    @Column(name = "availCar", nullable = false)
    private Integer availCar;

    @Getter @Setter
    @Column(name = "availVan", nullable = false)
    private Integer availVan;

}
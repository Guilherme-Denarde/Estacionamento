package com.estacionamento.jose.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Table(name = "settings", schema = "public")
public class Setting  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    @Setter
    private Long id;

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

    @PrePersist
    public void defaultSetting(){
        this.id = 1L;
        this.valueHour = BigDecimal.valueOf(10);
        this.valueTax = BigDecimal.valueOf(1);
        this.startShift = LocalTime.parse("07:00:00");
        this.endShift = LocalTime.parse("20:00:00");
        this.timeForDiscount = LocalTime.parse("00:02:00");
        this.generateDiscount = true;
        this.availBike = 15;
        this.availCar = 30;
        this.availVan = 5;

    }

}
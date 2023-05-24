package com.estacionamento.jose.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;

import java.time.LocalDateTime;

@Entity
@Table(name = "movement", schema = "public")
public class Movement extends AbstractEntity{

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "vehicle", nullable = false)
    private Vehicle vehicleId;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "condutor", nullable = false)
    private Conductor conductorId;

    @Getter @Setter
    @Column(name = "enter", nullable = false)
    private LocalDateTime enter;
    @Getter @Setter
    @Column(name = "exit")
    private LocalDateTime exit;
    @Getter @Setter
    @Column(name = "time")
    private LocalTime time;
    @Getter @Setter
    @Column(name = "time_discount")
    private LocalTime timeDiscount;
    @Getter @Setter
    @Column(name = "time_tax")
    private LocalTime timeTax;
    @Getter @Setter
    @Column(name = "discount_value")
    private Long discountValue;
    @Getter @Setter
    @Column(name = "tax_value")
    private BigDecimal valueTax;
    @Getter @Setter
    @Column(name = "total_value")
    private BigDecimal valueTotal;
    @Getter @Setter
    @Column(name = "hour_value")
    private BigDecimal valueHour;
    @Getter @Setter
    @Column(name = "hour_tax_value")
    private BigDecimal hourTaxvalue;

    @PrePersist
    public void fillmovement(){
        this.enter=LocalDateTime.now();
        this.exit=LocalDateTime.now();
        this.timeDiscount=LocalTime.now();
        this.timeTax=LocalTime.now();
    }
}

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

    @PrePersist
    public void fillmovement(){
        this.enter=LocalDateTime.now();
        this.exit=LocalDateTime.now();
    }
}

package com.estacionamento.jose.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalTime;

@Entity
@Table(name = "Conductor", schema = "public")
public class Conductor extends AbstractEntity {
    @Getter @Setter
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Getter @Setter
    @Column(name = "cpf", nullable = false, length = 20, unique = true)
    private String cpf;

    @Getter @Setter
    @Column(name = "telephone", nullable = false, length = 20)
    private String telephone;

    @Getter @Setter
    @Column(name = "payedTime")
    private LocalTime payedTime;

    @Getter @Setter
    @Column(name = "cutTime")
    private LocalTime cutTime;

    @PrePersist
    private void fillTime() {
        this.payedTime = LocalTime.now();
        this.cutTime = LocalTime.now();
    }
}

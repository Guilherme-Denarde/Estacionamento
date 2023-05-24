package com.estacionamento.jose.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "vehicle", schema = "public")
public class Vehicle extends AbstractEntity{

    @Getter @Setter
    @Column(name = "plate", length = 10, nullable = false, unique = true)
    private String plate;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "model")
    private Model modelId;

    @Getter @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "color", nullable = false)
    private Color color;

    @Getter @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private Type type;

    @Getter @Setter
    @Column(name = "year", nullable = false)
    private Integer year;

}

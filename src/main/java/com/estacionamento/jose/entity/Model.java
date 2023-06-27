package com.estacionamento.jose.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "model", schema = "public")
public class Model extends AbstractEntity{

    @Getter @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @Getter @Setter
    @Column(name = "ano", nullable = false)
    private Long ano;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brandId", unique = true,nullable = false)
    private Brand brandId;

}

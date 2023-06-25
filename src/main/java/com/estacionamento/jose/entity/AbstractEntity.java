package com.estacionamento.jose.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Optional;


@MappedSuperclass
@Table(name = "abstractEntity",schema = "public")
public class AbstractEntity {

    @Id @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Getter @Setter
    @Column(name = "cadastro", nullable = false)
    private LocalDateTime cadastro;

    @Getter @Setter
    @Column(name = "atualizacao")
    private LocalDateTime atualizacao;

    @Getter @Setter
    @Column(name = "active", nullable = false)
    private boolean active;

    @PrePersist
    private void prePersist(){
        this.cadastro = LocalDateTime.now();
        this.atualizacao = LocalDateTime.now();
        this.active = true;
    }

//    public abstract Optional<Object> stream();
}
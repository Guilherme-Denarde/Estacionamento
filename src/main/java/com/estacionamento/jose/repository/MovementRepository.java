package com.estacionamento.jose.repository;

import com.estacionamento.jose.entity.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {

    @Query("FROM Movement WHERE active = true")
    List<Movement> findByActiveMovement(@Param("active")final  boolean active);

    @Query("FROM Movement WHERE exit = null")
    List<Movement> findByAvailable();


}

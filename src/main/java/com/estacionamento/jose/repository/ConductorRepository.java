package com.estacionamento.jose.repository;

import com.estacionamento.jose.entity.Brand;
import com.estacionamento.jose.entity.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ConductorRepository extends JpaRepository<Conductor, Long> {


    @Query("FROM Conductor WHERE active = true")
    List<Conductor> findByAtivo(@Param("active")final boolean ativo);

    @Query("FROM Conductor WHERE name = :name AND id = :id")
    List<Conductor> findByNomePut(@Param("name") final String name, @Param("id")final Long id);

}

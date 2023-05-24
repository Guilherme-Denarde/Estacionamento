package com.estacionamento.jose.repository;

import com.estacionamento.jose.entity.Brand;
import com.estacionamento.jose.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long>{

    List<Model>findAll();

    @Query("FROM Model WHERE active = true")
    List<Model> findByActiveModel(@Param("active")final boolean active);

    @Query("FROM Model WHERE name = :name AND id = :id")
    List<Model> findByNomePut(@Param("name") final String name, @Param("id")final Long id);
}

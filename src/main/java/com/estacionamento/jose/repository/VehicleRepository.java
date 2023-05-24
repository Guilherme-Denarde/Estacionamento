package com.estacionamento.jose.repository;

import com.estacionamento.jose.entity.Movement;
import com.estacionamento.jose.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query("FROM Vehicle WHERE active = true")
    List<Vehicle> findByActiveVehicle(@Param("active")final boolean active);

    @Query("FROM Vehicle WHERE plate = :plate")
    List<Vehicle> findByPlate(@Param("plate")final String Plate);

    @Query("FROM Vehicle WHERE plate = :plate AND id = :id")
    List<Vehicle> findByPlatePut(@Param("plate") final String plate, @Param("id")final Long id);

    @Query("FROM Movement WHERE vehicleId = :vehicle AND active = true")
    List<Movement> findvehicleActiveMovimentacao(@Param("vehicle") final Vehicle vehicle);

}

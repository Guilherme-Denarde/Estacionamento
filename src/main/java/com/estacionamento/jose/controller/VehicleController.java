package com.estacionamento.jose.controller;

import com.estacionamento.jose.entity.Vehicle;
import com.estacionamento.jose.repository.VehicleRepository;
import com.estacionamento.jose.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/api/vehicle")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/id")
    public ResponseEntity<?> getById(@RequestParam("id") final Long id){
        Vehicle vehicle = this.vehicleRepository.findById(id).orElse(null);

        return vehicle == null
                ? ResponseEntity.badRequest().body("Nenhum valor encontrado")
                : ResponseEntity.ok(vehicle);
    }

    @GetMapping("/active")
    public ResponseEntity<?> findByActiveVehicle(@Param("active") final boolean active){
        return ResponseEntity.ok(this.vehicleRepository.findByActiveVehicle(active));
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){

        try{
            List<?> m_marca = vehicleRepository.findAll();
            return new ResponseEntity<>(m_marca, HttpStatus.OK);
        }catch (Exception e){

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> signup(@RequestBody final Vehicle vehicle){
        try {
            this.vehicleService.signup(vehicle);
            return ResponseEntity.ok("Registrado cadastrado com Sucesso");
        }
        catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<?> edit(@RequestParam("id") final Long id,@RequestBody final Vehicle vehicle){

        try{
            this.vehicleService.edit(vehicle, id);
            return ResponseEntity.ok("Registro atualizado com sucesso");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete (@RequestParam ("id") final Long id) {
        final Vehicle vehicleBanco = this.vehicleRepository.findById(id).orElse(null);

        this.vehicleService.delete(vehicleBanco);

        return ResponseEntity.ok("Veiculo deletado com sucesso");
    }

}
package com.estacionamento.jose.controller;

import com.estacionamento.jose.entity.Conductor;
import com.estacionamento.jose.repository.ConductorRepository;
import com.estacionamento.jose.service.ConductorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/conductor")
public class ConductorController {
    @Autowired
    private ConductorRepository conductorRepository;

    @Autowired
    private ConductorService conductorService;

    @GetMapping("/id")
    public ResponseEntity<?> findById(@RequestParam("id") final Long id){
        Conductor cur_condutor = this.conductorRepository.findById(id).orElse(null);

        return cur_condutor == null
                ? ResponseEntity.badRequest().body("Nenhum valor encontrado")
                : ResponseEntity.ok(cur_condutor);
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){

        try{
            List<?> m_marca = conductorRepository.findAll();
            return new ResponseEntity<>(m_marca, HttpStatus.OK);
        }catch (Exception e){

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/ativo")
    public ResponseEntity<?> findByAtivo(@Param("ativo") final boolean ativo){
        return ResponseEntity.ok(this.conductorRepository.findByAtivo(ativo));
    }

    @PostMapping("/add")
    public ResponseEntity<?> cadastrar(@RequestBody final Conductor conductor){
        try {
            this.conductorRepository.save(conductor);
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
    public ResponseEntity<?> edit(@RequestParam("id") final Long id,@RequestBody final Conductor conductor){
        try{
            this.conductorService.edit(conductor, id);
            return ResponseEntity.ok("Registro atualizacao com sucesso");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }


    @DeleteMapping
    public ResponseEntity<?> deletar(@RequestParam ("id") final Long id){

        final Conductor cc = this.conductorRepository.findById(id).orElse(null);

        this.conductorService.delete(cc);

        return ResponseEntity.ok("Marca deletada com sucesso");
    }
}

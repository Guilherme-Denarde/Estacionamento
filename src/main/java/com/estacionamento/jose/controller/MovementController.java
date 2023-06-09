package com.estacionamento.jose.controller;

//import com.estacionamento.jose.Give;
import com.estacionamento.jose.entity.Movement;
import com.estacionamento.jose.repository.MovementRepository;
import com.estacionamento.jose.service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/movement")
public class MovementController {

    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private MovementService movementService;

    @GetMapping("/id")
    public ResponseEntity<?> findById(@RequestParam("id") final Long id){
        Movement cur_movement = this.movementRepository.findById(id).orElse(null);

        return cur_movement == null
                ? ResponseEntity.badRequest().body("Nenhum valor encontrado")
                : ResponseEntity.ok(cur_movement);
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){

        try{
            List<?> m_movement = movementRepository.findAll();
            return new ResponseEntity<>(m_movement, HttpStatus.OK);
        }catch (Exception e){

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/ativo")
    public ResponseEntity<?> findByActiveMovement(@Param("active") final boolean active){
        return ResponseEntity.ok(this.movementRepository.findByActiveMovement(active));
    }

    @PostMapping("/add")
    public ResponseEntity<?> save(@RequestBody final Movement movement){
        try {
            this.movementService.save(movement);
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
    public ResponseEntity<?> edited(@RequestParam("id") final Long id,@RequestBody final Movement movement){

        try{
            this.movementService.edited(movement, id);
            return ResponseEntity.ok("Registro atualizacao com sucesso");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/exit")
    public ResponseEntity<?> setExit(@RequestParam("id") final Long id){
        try{
            this.movementService.Exit(id);

            return ResponseEntity.ok("Relatorio feito");
        }
        catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

//    @PutMapping("/saida/{id}")
//    public ResponseEntity<?> saida(@PathVariable("id") final Long id){
//        try{
//            this.movimentacaoService.saida(id);
//            return ResponseEntity.ok("Registro realizado e relatorio gerado");
//        } catch (RuntimeException erro){
//            return ResponseEntity.badRequest().body("Não conseguio sair"+erro.getMessage());
//        }
//    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deletar (@RequestParam ("id") final Long id){

        final Movement bb = this.movementRepository.findById(id).orElse(null);

        this.movementService.delete(bb);

        return ResponseEntity.ok("Model deletada com sucesso");
    }

}

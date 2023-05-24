package com.estacionamento.jose.controller;

import com.estacionamento.jose.entity.Model;
import com.estacionamento.jose.repository.ModelRepository;
import com.estacionamento.jose.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/model")
public class ModelController {

        @Autowired
        private ModelRepository modelRepository;

        @Autowired
        private ModelService modelService;

        @GetMapping("/id")
        public ResponseEntity<?> findById(@RequestParam("id") final Long id){
            Model cur_model = this.modelRepository.findById(id).orElse(null);

            return cur_model == null
                    ? ResponseEntity.badRequest().body("Nenhum valor encontrado")
                    : ResponseEntity.ok(cur_model);
        }
        //
        @GetMapping("/findAll")
        public ResponseEntity<?> findAll(){

            try{
                List<?> m_model = modelRepository.findAll();
                return new ResponseEntity<>(m_model, HttpStatus.OK);
            }catch (Exception e){

                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        @GetMapping("/ativo")
        public ResponseEntity<?> findByActiveModel(@Param("active") final boolean active){
            return ResponseEntity.ok(this.modelRepository.findByActiveModel(active));
        }

        @PostMapping("/add")
        public ResponseEntity<?> cadastrar(@RequestBody final Model model){
            try {
                this.modelService.signup(model);
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
        public ResponseEntity<?> edited(@RequestParam("id") final Long id,@RequestBody final Model model){

            try{
                this.modelService.edited(model, id);
                return ResponseEntity.ok("Registro atualizacao com sucesso");
            }
            catch (DataIntegrityViolationException e){
                return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
            }
            catch (RuntimeException e){
                return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
            }
        }


        @DeleteMapping("/delete")
        public ResponseEntity<?> deletar (@RequestParam ("id") final Long id){

            final Model bb = this.modelRepository.findById(id).orElse(null);

            this.modelService.delete(bb);

            return ResponseEntity.ok("Model deletada com sucesso");
        }
    }


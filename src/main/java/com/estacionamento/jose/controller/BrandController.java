package com.estacionamento.jose.controller;
import com.estacionamento.jose.entity.Brand;
import com.estacionamento.jose.repository.BrandRepository;
import com.estacionamento.jose.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/brand")
public class BrandController {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private BrandService brandService;

    @GetMapping("/id")
    public ResponseEntity<?> findById(@RequestParam("id") final Long id){
        Brand cur_brand = this.brandRepository.findById(id).orElse(null);

        return cur_brand == null
                ? ResponseEntity.badRequest().body("Nenhum valor encontrado")
                : ResponseEntity.ok(cur_brand);
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){

        try{
            List<?> m_marca = brandRepository.findAll();
            return new ResponseEntity<>(m_marca, HttpStatus.OK);
        }catch (Exception e){

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/ativo")
    public ResponseEntity<?> findByActive(@Param("active") final boolean active){
        return ResponseEntity.ok(this.brandRepository.findByActive(active));
    }

    @PostMapping("/add")
    public ResponseEntity<?> cadastrar(@RequestBody final Brand brand){
        try {
            this.brandService.cadastrar(brand);
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
    public ResponseEntity<?> edit(@RequestParam("id") final Long id,@RequestBody final Brand brand){

        try{
            this.brandService.edit(brand, id);
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

        final Brand bb = this.brandRepository.findById(id).orElse(null);

        this.brandService.delete(bb);

        return ResponseEntity.ok("Marca deletada com sucesso");
    }
}
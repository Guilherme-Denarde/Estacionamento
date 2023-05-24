package com.estacionamento.jose.controller;

import com.estacionamento.jose.entity.Movement;
import com.estacionamento.jose.entity.Setting;
import com.estacionamento.jose.repository.SettingRepository;
import com.estacionamento.jose.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/setting")
public class SettingController {

    @Autowired
    private SettingRepository settingRepository;

    @Autowired
    private SettingService settingService;

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){

        try{
            List<?> m_movement = settingRepository.findAll();
            return new ResponseEntity<>(m_movement, HttpStatus.OK);
        }catch (Exception e){

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<?> edited(@RequestParam("id") final Long id,@RequestBody final Setting setting){

        try{
            this.settingService.edit(setting, id);
            return ResponseEntity.ok("Registro atualizacao com sucesso");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}

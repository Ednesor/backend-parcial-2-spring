package com.gskate.springdemo.controller;

import com.gskate.springdemo.entity.dto.Profesor.ProfesorCreate;
import com.gskate.springdemo.entity.dto.Profesor.ProfesorEdit;
import com.gskate.springdemo.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/profesor")
public class ProfesorController {

    @Autowired
    ProfesorService profesorService;

    @PostMapping
    public ResponseEntity<?> create (@RequestBody ProfesorCreate profesor){
        try{
            return ResponseEntity.ok(profesorService.save(profesor));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocurrio un error " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll (){
        try{
            return ResponseEntity.ok(profesorService.findAll());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocurrio un error " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> edit (@RequestBody ProfesorEdit profesor, @RequestParam Long id){
        try{
            return ResponseEntity.ok(profesorService.edit(profesor, id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocurrio un error " + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete (@RequestParam Long id){
        try{
            profesorService.delete(id);
            return ResponseEntity.ok().body("Profesor eliminado con exito");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocurrio un error " + e.getMessage());
        }
    }

    @GetMapping("/findById")
    public ResponseEntity<?> findById (@RequestParam Long id){
        try{
            return ResponseEntity.ok(profesorService.findById(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocurrio un error " + e.getMessage());
        }
    }

    @PostMapping("/setCurso")
    public ResponseEntity<?> setCurso (@RequestParam Long idProfesor, @RequestParam Long idCurso){
        try{
            return ResponseEntity.ok(profesorService.asignarCurso(idProfesor, idCurso));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocurrio un error " + e.getMessage());
        }
    }
}

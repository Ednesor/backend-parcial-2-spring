package com.gskate.springdemo.controller;

import com.gskate.springdemo.entity.dto.Alumno.EstudianteCreate;
import com.gskate.springdemo.entity.dto.Alumno.EstudianteEdit;
import com.gskate.springdemo.entity.dto.Profesor.ProfesorCreate;
import com.gskate.springdemo.service.EstudianteService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/estudiante")
public class EstudianteController {

    @Autowired
    EstudianteService estudianteService;

    @PostMapping
    public ResponseEntity<?> create (@RequestBody EstudianteCreate e){
        try{
            return ResponseEntity.ok(estudianteService.save(e));
        }catch (Exception error){
            return ResponseEntity.badRequest().body("Ocurrio un error " + error.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll (){
        try{
            return ResponseEntity.ok(estudianteService.findAll());
        }catch (Exception error){
            return ResponseEntity.badRequest().body("Ocurrio un error " + error.getMessage());
        }
    }

    @GetMapping("/findById")
    public ResponseEntity<?> getById (@RequestParam Long id){
        try{
            return ResponseEntity.ok(estudianteService.findById(id));
        }catch (Exception error){
            return ResponseEntity.badRequest().body("Ocurrio un error " + error.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> edit (@RequestBody EstudianteEdit e, @RequestParam Long id){
        try{
            return ResponseEntity.ok(estudianteService.edit(e, id));
        }catch (Exception error){
            return ResponseEntity.badRequest().body("Ocurrio un error " + error.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete (@RequestParam Long id){
        try{
            estudianteService.delete(id);
            return ResponseEntity.ok().body("Estudiante eliminado correctamente");
        }catch (Exception error){
            return ResponseEntity.badRequest().body("Ocurrio un error " + error.getMessage());
        }
    }

    @GetMapping("/getCursosByEstudianteId")
    public ResponseEntity<?> getCursosByEstudianteId (@RequestParam Long estudianteId) {
        try {
            return ResponseEntity.ok(estudianteService.getCursosByEstudianteId(estudianteId));
        } catch (Exception error) {
            return ResponseEntity.badRequest().body("Ocurrio un error " + error.getMessage());
        }
    }

}

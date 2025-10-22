package com.gskate.springdemo.controller;

import com.gskate.springdemo.entity.dto.Curso.CursoCreate;
import com.gskate.springdemo.entity.dto.Curso.CursoEdit;
import com.gskate.springdemo.service.CursoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    CursoService cursoService;

    @PostMapping
    public ResponseEntity<?> create (@RequestBody CursoCreate c){
        try{
            return ResponseEntity.ok(cursoService.save(c));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocurrio un error " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll (){
        try{
            return ResponseEntity.ok(cursoService.findAll());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocurrio un error " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> edit (@RequestBody CursoEdit c, @RequestParam Long id){
        try{
            return ResponseEntity.ok(cursoService.edit(c, id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocurrio un error " + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete (@RequestParam Long id){
        try{
            cursoService.delete(id);
            return ResponseEntity.ok().body("Curso eliminado con exito");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocurrio un error " + e.getMessage());
        }
    }
    @GetMapping("/findById")
    public ResponseEntity<?> findById (@RequestParam Long id) {
        try {
            return ResponseEntity.ok(cursoService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ocurrio un error " + e.getMessage());
        }
    }

    @PostMapping("/addEstudiante")
    public ResponseEntity<?> addEstudianteToCurso(@RequestParam Long cursoId, @RequestParam Long estudianteId){
        try{
            return ResponseEntity.ok(cursoService.addEstudianteToCurso(cursoId, estudianteId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocurrio un error " + e.getMessage());
        }
    }

}

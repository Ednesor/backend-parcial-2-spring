package com.gskate.springdemo.entity.dto.Profesor;

import com.gskate.springdemo.entity.Curso;
import com.gskate.springdemo.entity.dto.Curso.CursoDTO;

import java.util.List;

public record ProfesorDTO (
        Long id,
        String nombre,
        String email,
        List<CursoDTO> cursos
){

}

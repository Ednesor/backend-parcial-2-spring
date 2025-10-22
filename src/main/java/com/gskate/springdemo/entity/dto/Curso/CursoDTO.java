package com.gskate.springdemo.entity.dto.Curso;

import com.gskate.springdemo.entity.Profesor;
import com.gskate.springdemo.entity.dto.Alumno.EstudianteDTO;

import java.util.List;

public record CursoDTO(
        Long id,
        String nombre,
        Long profesorId,
        String profesorNombre
//        List<EstudianteDTO> estudiantes
) {
}

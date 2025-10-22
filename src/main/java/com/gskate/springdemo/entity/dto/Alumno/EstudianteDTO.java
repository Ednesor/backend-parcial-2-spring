package com.gskate.springdemo.entity.dto.Alumno;

import com.gskate.springdemo.entity.dto.Curso.CursoDTO;

import java.util.List;

public record EstudianteDTO(
        Long id,
        String nombre,
        String matricula,
        List<CursoDTO> cursos
) {
}

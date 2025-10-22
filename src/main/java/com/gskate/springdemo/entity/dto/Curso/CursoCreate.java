package com.gskate.springdemo.entity.dto.Curso;

import com.gskate.springdemo.entity.Profesor;

public record CursoCreate(
        String nombre,
        Long profesorId
) {
}

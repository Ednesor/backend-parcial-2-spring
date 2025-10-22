package com.gskate.springdemo.entity.dto.mapper;

import com.gskate.springdemo.entity.Profesor;
import com.gskate.springdemo.entity.dto.Curso.CursoDTO;
import com.gskate.springdemo.entity.dto.Profesor.ProfesorCreate;
import com.gskate.springdemo.entity.dto.Profesor.ProfesorDTO;
import com.gskate.springdemo.entity.dto.Profesor.ProfesorEdit;

import java.util.List;
import java.util.stream.Collectors;

public class ProfesorMapper {
    public static Profesor toEntity(ProfesorCreate p){
        return Profesor.builder()
                .nombre(p.nombre())
                .email(p.email())
                .build();
    }

    public static ProfesorDTO toDto(Profesor p){
        if (p==null) return null;
        List<CursoDTO> cursos = p.getCursos() == null
                ? List.of()
                : p.getCursos().stream().map(CursoMapper::toDto).toList();
        return new ProfesorDTO(
                p.getId(),
                p.getNombre(),
                p.getEmail(),
                cursos
        );
    }

    public static void updateEntity(Profesor profesor, ProfesorEdit edit){
        profesor.setNombre(edit.nombre());
        profesor.setEmail(edit.email());
    }
}

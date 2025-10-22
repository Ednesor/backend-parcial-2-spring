package com.gskate.springdemo.entity.dto.mapper;

import com.gskate.springdemo.entity.Estudiante;
import com.gskate.springdemo.entity.dto.Alumno.EstudianteCreate;
import com.gskate.springdemo.entity.dto.Alumno.EstudianteDTO;
import com.gskate.springdemo.entity.dto.Alumno.EstudianteEdit;
import com.gskate.springdemo.entity.dto.Curso.CursoDTO;

import java.util.List;

public class EstudianteMapper {
    public static Estudiante toEntity(EstudianteCreate p){
        return Estudiante.builder()
                .nombre(p.nombre())
                .matricula(p.matricula())
                .build();
    }

    public static EstudianteDTO toDto(Estudiante e){
        if (e == null) return null;
        List<CursoDTO> cursos = e.getCursos() == null
                ? List.of()
                : e.getCursos().stream().map(CursoMapper::toDto).toList();
        return new EstudianteDTO(
                e.getId(),
                e.getNombre(),
                e.getMatricula(),
                cursos
        );
    }

    public static void updateEntity(Estudiante estudiante, EstudianteEdit edit){
        estudiante.setNombre(edit.nombre());
        estudiante.setMatricula(edit.matricula());
    }
}

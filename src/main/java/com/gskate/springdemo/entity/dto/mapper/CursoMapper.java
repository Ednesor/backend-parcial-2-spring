package com.gskate.springdemo.entity.dto.mapper;

import com.gskate.springdemo.entity.Curso;
import com.gskate.springdemo.entity.dto.Alumno.EstudianteDTO;
import com.gskate.springdemo.entity.dto.Curso.CursoCreate;
import com.gskate.springdemo.entity.dto.Curso.CursoDTO;
import com.gskate.springdemo.entity.dto.Curso.CursoEdit;

import java.util.List;

public class CursoMapper {

    public static Curso toEntity(CursoCreate c){
        return Curso.builder()
                .nombre(c.nombre())
                .build();
    }

    public static CursoDTO toDto(Curso c){
        if (c== null) return null;
        Long profesorId = c.getProfesor() != null ? c.getProfesor().getId() : null;
        String profesorNombre = c.getProfesor() != null ? c.getProfesor().getNombre() : null;
//        List<EstudianteDTO> estudiantesDto = c.getEstudiantes() == null
//                ? List.of()
//                : c.getEstudiantes().stream()
//                    .map(EstudianteMapper::toDtoWithoutCursos)
//                    .toList();
        return new CursoDTO(
                c.getId(),
                c.getNombre(),
                profesorId,
                profesorNombre
        );
    }

    public static void updateEntity(Curso curso, CursoEdit edit){
        curso.setNombre(edit.nombre());
    }
}

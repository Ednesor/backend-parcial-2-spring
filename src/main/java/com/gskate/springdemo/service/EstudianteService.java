package com.gskate.springdemo.service;

import com.gskate.springdemo.entity.dto.Alumno.EstudianteCreate;
import com.gskate.springdemo.entity.dto.Alumno.EstudianteDTO;
import com.gskate.springdemo.entity.dto.Alumno.EstudianteEdit;
import com.gskate.springdemo.entity.dto.Curso.CursoDTO;

import java.util.List;

public interface EstudianteService {
    EstudianteDTO save(EstudianteCreate e);
    EstudianteDTO edit(EstudianteEdit e, Long id);
    EstudianteDTO findById(Long id);
    List<EstudianteDTO> findAll();
    void delete(Long id);
    List<CursoDTO> getCursosByEstudianteId(Long estudianteId);
}

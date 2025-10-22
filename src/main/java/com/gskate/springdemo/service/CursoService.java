package com.gskate.springdemo.service;

import com.gskate.springdemo.entity.dto.Curso.CursoCreate;
import com.gskate.springdemo.entity.dto.Curso.CursoDTO;
import com.gskate.springdemo.entity.dto.Curso.CursoEdit;

import java.util.List;

public interface CursoService {
    CursoDTO save(CursoCreate c);
    CursoDTO edit(CursoEdit c, Long id);
    CursoDTO findById(Long id);
    List<CursoDTO> findAll();
    void delete(Long id);
    CursoDTO addEstudianteToCurso(Long cursoId, Long estudianteId);
}

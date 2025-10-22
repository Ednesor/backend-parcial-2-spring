package com.gskate.springdemo.service;

import com.gskate.springdemo.entity.Curso;
import com.gskate.springdemo.entity.dto.Curso.CursoDTO;
import com.gskate.springdemo.entity.dto.Profesor.ProfesorCreate;
import com.gskate.springdemo.entity.dto.Profesor.ProfesorDTO;
import com.gskate.springdemo.entity.dto.Profesor.ProfesorEdit;

import java.util.List;

public interface ProfesorService {
    ProfesorDTO save(ProfesorCreate p);
    ProfesorDTO edit(ProfesorEdit p, Long id);
    ProfesorDTO findById(Long id);
    List<ProfesorDTO> findAll();
    void delete(Long id);
    List<CursoDTO> asignarCurso(Long profesorId, Long cursoId);
}

package com.gskate.springdemo.service;

import com.gskate.springdemo.entity.Curso;
import com.gskate.springdemo.entity.Estudiante;
import com.gskate.springdemo.entity.Profesor;
import com.gskate.springdemo.entity.dto.Curso.CursoCreate;
import com.gskate.springdemo.entity.dto.Curso.CursoDTO;
import com.gskate.springdemo.entity.dto.Curso.CursoEdit;
import com.gskate.springdemo.entity.dto.mapper.CursoMapper;
import com.gskate.springdemo.repository.CursoRepository;
import com.gskate.springdemo.repository.EstudianteRepository;
import com.gskate.springdemo.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoServiceImp implements CursoService{
    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    ProfesorRepository profesorRepository;

    @Autowired
    EstudianteRepository estudianteRepository;

    @Override
    public CursoDTO save(CursoCreate c) {
        Curso curso = CursoMapper.toEntity(c);

        if (c.profesorId() != null){
            Profesor profesor = profesorRepository.findById(c.profesorId())
                    .orElseThrow(() -> new NullPointerException("No se encontro el profesor con id " + c.profesorId()));
            curso.setProfesor(profesor);
        }

        curso = cursoRepository.save(curso);
        return CursoMapper.toDto(curso);
    }
    @Override
    public CursoDTO edit(CursoEdit c, Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("No se encontro el curso con id " + id));
        CursoMapper.updateEntity(curso, c);

        if(c.profesorId() != null){
            Profesor profesor = profesorRepository.findById(c.profesorId())
                    .orElseThrow(() -> new NullPointerException("No se encontro el profesor con id " + c.profesorId()));
            curso.setProfesor(profesor);
        }
        curso = cursoRepository.save(curso);
        return CursoMapper.toDto(curso);
    }
    @Override
    public CursoDTO findById(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("No se encontro el curso con id " + id));
        curso.getEstudiantes().size();
        return CursoMapper.toDto(curso);
    }
    @Override
    public List<CursoDTO> findAll() {
        return cursoRepository.findAll().stream().map(CursoMapper::toDto).toList();
    }
    @Override
    public void delete(Long id) {
        cursoRepository.deleteById(id);
    }
    @Override
    public CursoDTO addEstudianteToCurso(Long cursoId, Long estudianteId) {
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new NullPointerException("No se encontro el curso con id " + cursoId));
        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new NullPointerException("No se encontro el estudiante con id " + estudianteId));
        curso.addEstudiante(estudiante);
        curso = cursoRepository.save(curso);
        return CursoMapper.toDto(curso);
    }


}

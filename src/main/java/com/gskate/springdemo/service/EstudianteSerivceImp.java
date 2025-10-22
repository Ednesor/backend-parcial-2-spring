package com.gskate.springdemo.service;

import com.gskate.springdemo.entity.Estudiante;
import com.gskate.springdemo.entity.dto.Alumno.EstudianteCreate;
import com.gskate.springdemo.entity.dto.Alumno.EstudianteDTO;
import com.gskate.springdemo.entity.dto.Alumno.EstudianteEdit;
import com.gskate.springdemo.entity.dto.Curso.CursoDTO;
import com.gskate.springdemo.entity.dto.mapper.EstudianteMapper;
import com.gskate.springdemo.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteSerivceImp implements EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Override
    public EstudianteDTO save(EstudianteCreate e) {
        Estudiante estudiante = EstudianteMapper.toEntity(e);
        estudiante = estudianteRepository.save(estudiante);
        return EstudianteMapper.toDto(estudiante);
    }

    @Override
    public EstudianteDTO edit(EstudianteEdit e, Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("No se encontro el estudiante con id " + id));
        EstudianteMapper.updateEntity(estudiante, e);
        estudiante = estudianteRepository.save(estudiante);
        return EstudianteMapper.toDto(estudiante);
    }

    @Override
    public EstudianteDTO findById(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("No se encontro el estudiante con id " + id));
        return EstudianteMapper.toDto(estudiante);
    }

    @Override
    public void delete(Long id) {
        estudianteRepository.deleteById(id);
    }

    @Override
    public List<EstudianteDTO> findAll() {
        return estudianteRepository.findAll().stream().map(EstudianteMapper::toDto).toList();
    }

    @Override
    public List<CursoDTO> getCursosByEstudianteId(Long estudianteId) {
        List<CursoDTO> cursos = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new NullPointerException("No se encontro el estudiante con id " + estudianteId))
                .getCursos()
                .stream()
                .map(curso -> new CursoDTO(
                        curso.getId(),
                        curso.getNombre(),
                        curso.getProfesor().getId(),
                        curso.getProfesor().getNombre()
                ))
                .toList();
        return cursos;
    }
}

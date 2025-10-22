package com.gskate.springdemo.service;

import com.gskate.springdemo.entity.Curso;
import com.gskate.springdemo.entity.Profesor;
import com.gskate.springdemo.entity.dto.Curso.CursoDTO;
import com.gskate.springdemo.entity.dto.Profesor.ProfesorCreate;
import com.gskate.springdemo.entity.dto.Profesor.ProfesorDTO;
import com.gskate.springdemo.entity.dto.Profesor.ProfesorEdit;
import com.gskate.springdemo.entity.dto.mapper.CursoMapper;
import com.gskate.springdemo.entity.dto.mapper.ProfesorMapper;
import com.gskate.springdemo.repository.CursoRepository;
import com.gskate.springdemo.repository.ProfesorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorServiceImp implements ProfesorService {
    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public ProfesorDTO save(ProfesorCreate p){
        Profesor profesor = ProfesorMapper.toEntity(p);
        profesor = profesorRepository.save(profesor);
        return ProfesorMapper.toDto(profesor);
    }

    @Override
    public ProfesorDTO edit(ProfesorEdit p, Long id){
        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("No se encontro el profesor con el id "+id));
        ProfesorMapper.updateEntity(profesor, p);
        profesor = profesorRepository.save(profesor);
        return ProfesorMapper.toDto(profesor);
    }

    @Override
    @Transactional
    public ProfesorDTO findById(Long id){
        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("No se encontro el profesor con el id "+id));
        profesor.getCursos().size();
        return ProfesorMapper.toDto(profesor);
    }

    @Override
    public void delete(Long id){
        profesorRepository.deleteById(id);
    }

    @Override
    public List<ProfesorDTO> findAll(){
        return profesorRepository.findAll().stream().map(ProfesorMapper::toDto).toList();
    }

    @Override
    public List<CursoDTO> asignarCurso(Long profesorId, Long cursoId) {
        Profesor profesor = profesorRepository.findById(profesorId)
                .orElseThrow(() -> new NullPointerException("No se encontro el profesor con el id " + profesorId));
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new NullPointerException("No se encontro el curso con el id " + cursoId));

        profesor.addCurso(curso);

        profesorRepository.save(profesor);


        return profesor.getCursos().stream()
                .map(CursoMapper::toDto)
                .toList();
    }
}

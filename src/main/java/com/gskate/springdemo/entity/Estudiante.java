package com.gskate.springdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Estudiante extends Base {
    private String nombre;
    private String matricula;

    @ManyToMany(mappedBy = "estudiantes", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Curso> cursos = new HashSet<>();

    public void addCurso(Curso curso) {
        cursos.add(curso);
        curso.getEstudiantes().add(this);
    }

    public void removeCurso(Curso curso) {
        cursos.remove(curso);
        curso.getEstudiantes().remove(this);
    }
}

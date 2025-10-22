package com.gskate.springdemo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Profesor extends Base{
    private String nombre;
    private String email;

    @OneToMany(mappedBy = "profesor", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Curso> cursos = new ArrayList<>();

    public void addCurso(Curso curso){
        this.cursos.add(curso);
        curso.setProfesor(this);
    }

    public void removeCurso(Curso curso){
        this.cursos.remove(curso);
        curso.setProfesor(null);
    }
}

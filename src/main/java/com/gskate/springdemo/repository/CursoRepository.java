package com.gskate.springdemo.repository;

import com.gskate.springdemo.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}

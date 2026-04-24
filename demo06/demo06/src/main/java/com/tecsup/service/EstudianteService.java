package com.tecsup.service;

import com.tecsup.dto.EstudianteDTO;
import com.tecsup.model.Curso;
import com.tecsup.model.Estudiante;
import com.tecsup.repository.CursoRepository;
import com.tecsup.repository.EstudianteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final CursoRepository cursoRepository;

    public EstudianteService(EstudianteRepository estudianteRepository, CursoRepository cursoRepository) {
        this.estudianteRepository = estudianteRepository;
        this.cursoRepository = cursoRepository;
    }

    @Transactional
    public Estudiante crear(EstudianteDTO dto) {
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(dto.getNombre());
        estudiante.setEmail(dto.getEmail());
        estudiante.setCursos(obtenerCursos(dto.getCursoIds()));
        return estudianteRepository.save(estudiante);
    }

    public List<Estudiante> listar() {
        return estudianteRepository.findAll();
    }

    public Estudiante obtenerPorId(Long id) {
        return estudianteRepository.findById(id).orElse(null);
    }

    @Transactional
    public Estudiante agregarCurso(Long estudianteId, Long cursoId) {
        Estudiante estudiante = obtenerEstudianteExistente(estudianteId);
        Curso curso = obtenerCursoExistente(cursoId);

        if (estudiante.getCursos().stream().anyMatch(item -> item.getId().equals(cursoId))) {
            throw new IllegalArgumentException("El estudiante ya esta inscrito en este curso");
        }

        estudiante.getCursos().add(curso);
        return estudianteRepository.save(estudiante);
    }

    @Transactional
    public Estudiante quitarCurso(Long estudianteId, Long cursoId) {
        Estudiante estudiante = obtenerEstudianteExistente(estudianteId);
        boolean eliminado = estudiante.getCursos().removeIf(curso -> curso.getId().equals(cursoId));

        if (!eliminado) {
            throw new IllegalArgumentException("El estudiante no esta inscrito en el curso indicado");
        }

        return estudianteRepository.save(estudiante);
    }

    public Set<Curso> listarCursosDeEstudiante(Long estudianteId) {
        return obtenerEstudianteExistente(estudianteId).getCursos();
    }

    private Set<Curso> obtenerCursos(Set<Long> cursoIds) {
        Set<Curso> cursos = new HashSet<>();
        if (cursoIds == null || cursoIds.isEmpty()) {
            return cursos;
        }

        for (Long cursoId : cursoIds) {
            Curso curso = obtenerCursoExistente(cursoId);
            if (!cursos.add(curso)) {
                throw new IllegalArgumentException("No se puede repetir la inscripcion al mismo curso");
            }
        }

        return cursos;
    }

    private Estudiante obtenerEstudianteExistente(Long estudianteId) {
        Estudiante estudiante = obtenerPorId(estudianteId);
        if (estudiante == null) {
            throw new IllegalArgumentException("Estudiante no encontrado");
        }
        return estudiante;
    }

    private Curso obtenerCursoExistente(Long cursoId) {
        Curso curso = cursoRepository.findById(cursoId).orElse(null);
        if (curso == null) {
            throw new IllegalArgumentException("Curso no encontrado con id: " + cursoId);
        }
        return curso;
    }
}

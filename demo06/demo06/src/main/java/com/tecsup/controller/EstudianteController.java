package com.tecsup.controller;

import com.tecsup.dto.EstudianteDTO;
import com.tecsup.model.Curso;
import com.tecsup.model.Estudiante;
import com.tecsup.service.EstudianteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @PostMapping
    public ResponseEntity<Estudiante> crear(@Valid @RequestBody EstudianteDTO dto) {
        return ResponseEntity.status(201).body(estudianteService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<Estudiante>> listar() {
        return ResponseEntity.ok(estudianteService.listar());
    }

    @PostMapping("/{estudianteId}/cursos/{cursoId}")
    public ResponseEntity<Estudiante> agregarCurso(@PathVariable Long estudianteId, @PathVariable Long cursoId) {
        return ResponseEntity.ok(estudianteService.agregarCurso(estudianteId, cursoId));
    }

    @DeleteMapping("/{estudianteId}/cursos/{cursoId}")
    public ResponseEntity<Estudiante> quitarCurso(@PathVariable Long estudianteId, @PathVariable Long cursoId) {
        return ResponseEntity.ok(estudianteService.quitarCurso(estudianteId, cursoId));
    }

    @GetMapping("/{estudianteId}/cursos")
    public ResponseEntity<Set<Curso>> listarCursos(@PathVariable Long estudianteId) {
        return ResponseEntity.ok(estudianteService.listarCursosDeEstudiante(estudianteId));
    }
}

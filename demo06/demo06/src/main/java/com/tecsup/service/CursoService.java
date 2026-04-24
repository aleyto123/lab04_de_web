package com.tecsup.service;

import com.tecsup.dto.CursoDTO;
import com.tecsup.model.Curso;
import com.tecsup.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public Curso crear(CursoDTO dto) {
        Curso curso = new Curso();
        curso.setNombre(dto.getNombre());
        curso.setCreditos(dto.getCreditos());
        return cursoRepository.save(curso);
    }

    public Curso obtenerPorId(Long id) {
        return cursoRepository.findById(id).orElse(null);
    }

    public List<Curso> listar() {
        return cursoRepository.findAll();
    }
}

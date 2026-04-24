package com.tecsup.service;

import com.tecsup.dto.PerfilDTO;
import com.tecsup.dto.UsuarioRegistroDTO;
import com.tecsup.model.Perfil;
import com.tecsup.model.Usuario;
import com.tecsup.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario registrar(UsuarioRegistroDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setUsername(dto.getUsername());
        usuario.setPassword(dto.getPassword());
        usuario.setPerfil(crearPerfil(dto.getPerfil()));
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public boolean eliminar(Long id) {
        Usuario usuario = obtenerPorId(id);
        if (usuario == null) {
            return false;
        }
        usuarioRepository.delete(usuario);
        return true;
    }

    private Perfil crearPerfil(PerfilDTO dto) {
        Perfil perfil = new Perfil();
        perfil.setNombreCompleto(dto.getNombreCompleto());
        perfil.setDireccion(dto.getDireccion());
        perfil.setTelefono(dto.getTelefono());
        return perfil;
    }
}

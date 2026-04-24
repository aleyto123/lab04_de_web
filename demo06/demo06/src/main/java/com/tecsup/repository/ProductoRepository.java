package com.tecsup.repository;

import com.tecsup.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List; // No olvides importar List

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // El método debe ir dentro de la interfaz y terminar en punto y coma
    List<Producto> findByNombreContaining(String nombre);

}
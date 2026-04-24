package com.tecsup.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class CursoDTO {

    @NotBlank(message = "El nombre del curso es obligatorio")
    private String nombre;

    @Min(value = 1, message = "Los creditos deben ser mayores a 0")
    private int creditos;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }
}

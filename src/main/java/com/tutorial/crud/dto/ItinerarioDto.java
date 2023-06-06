package com.tutorial.crud.dto;

import java.sql.Date;

public class ItinerarioDto {

    private int id;
    private int idUsuario;
    private String fechaCreacion;

    public ItinerarioDto() {
    }

    public ItinerarioDto(int id, int idUsuario, String fechaCreacion) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.fechaCreacion = fechaCreacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}

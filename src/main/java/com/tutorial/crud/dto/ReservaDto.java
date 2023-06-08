package com.tutorial.crud.dto;

import com.tutorial.crud.entity.Usuario;
import com.tutorial.crud.entity.Vuelo;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

public class ReservaDto {

    @NotNull
    private Usuario usuario;

    @NotNull
    private Vuelo vuelo;

    @NotBlank
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "El formato de fecha debe ser 'yyyy-MM-dd'")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaReserva;

    @NotBlank
    @Pattern(regexp = "\\d{2}:\\d{2}", message = "El formato de hora debe ser 'HH:mm'")
    @DateTimeFormat(pattern = "HH:mm")
    private String horaReserva;


    public ReservaDto() {
    }

    public ReservaDto(@NotNull Usuario usuario, @NotNull Vuelo vuelo, @NotBlank Date fechaReserva, @NotBlank Time horaReserva) {
        this.usuario = usuario;
        this.vuelo = vuelo;
        this.fechaReserva = fechaReserva;
        this.horaReserva = String.valueOf(horaReserva);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva.toLocalDate();
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public @NotBlank @Pattern(regexp = "\\d{2}:\\d{2}", message = "El formato de hora debe ser 'HH:mm'") String getHoraReserva() {
        return horaReserva;
    }

    public void setHoraReserva(Time horaReserva) {
        this.horaReserva = String.valueOf(horaReserva);
    }

    public java.util.Date getParsedDate() {
        return null;
    }
}

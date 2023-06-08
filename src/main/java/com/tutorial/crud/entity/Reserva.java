package com.tutorial.crud.entity;

import javax.persistence.*;

@Entity
@Table(name = "Reservas")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_vuelo")
    private Vuelo vuelo;

    @Column(name = "fecha_reserva")
    private java.sql.Date fechaReserva;

    @Column(name = "hora_reserva")
    private java.sql.Time horaReserva;

    @ManyToOne
    @JoinColumn(name = "id_itinerario")
    private Itinerario itinerario;

    public Reserva() {
    }

    public Reserva(Usuario usuario, Vuelo vuelo, java.sql.Date fechaReserva, java.sql.Time horaReserva) {
        this.usuario = usuario;
        this.vuelo = vuelo;
        this.fechaReserva = fechaReserva;
        this.horaReserva = horaReserva;
    }

    public java.sql.Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(java.sql.Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public java.sql.Time getHoraReserva() {
        return horaReserva;
    }

    public void setHoraReserva(java.sql.Time horaReserva) {
        this.horaReserva = horaReserva;
    }


    public void setId(int id) {
    }

    public void setItinerario(Itinerario itinerario) {
    }

    public void setUsuario(Usuario usuario) {
    }

    public void setVuelo(Vuelo vuelo) {
    }

    public void setFechaReserva(String fechaReserva) {
    }

    public void setHoraReserva(String horaReserva) {
    }

    // Getters y setters

    // ...
}

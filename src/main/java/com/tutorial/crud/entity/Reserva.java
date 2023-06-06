package com.tutorial.crud.entity;

import javax.persistence.*;
import java.util.Date;

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

    public void setId(int id) {
    }

    public void setItinerario(Itinerario itinerario) {
    }

    // Getters y setters

    // ...
}

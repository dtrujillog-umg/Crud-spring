package com.tutorial.crud.dto;

public class Mensaje {
    private String mensaje;
    private String tipoUsuario;

    public Mensaje(String mensaje, String tipoUsuario) {
        this.mensaje = mensaje;
        this.tipoUsuario = tipoUsuario;
    }

    public Mensaje(String camposInválidos) {

    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}

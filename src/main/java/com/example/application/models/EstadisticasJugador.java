package com.example.application.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class EstadisticasJugador {
    @Id
    private String id;
    private String nombreUsuario;
    private int partidasJugadas;
    private int partidasGanadas;
    private int partidasPerdidas;
    private int empates;

    // Constructor por defecto
    public EstadisticasJugador() {
    }

    // Constructor con todos los campos
    public EstadisticasJugador(String nombreUsuario, int partidasJugadas, int partidasGanadas, int partidasPerdidas, int empates) {
        this.nombreUsuario = nombreUsuario;
        this.partidasJugadas = partidasJugadas;
        this.partidasGanadas = partidasGanadas;
        this.partidasPerdidas = partidasPerdidas;
        this.empates = empates;
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getPartidasJugadas() {
        return partidasJugadas;
    }

    public void setPartidasJugadas(int partidasJugadas) {
        this.partidasJugadas = partidasJugadas;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    public void setPartidasGanadas(int partidasGanadas) {
        this.partidasGanadas = partidasGanadas;
    }

    public int getPartidasPerdidas() {
        return partidasPerdidas;
    }

    public void setPartidasPerdidas(int partidasPerdidas) {
        this.partidasPerdidas = partidasPerdidas;
    }

    public int getEmpates() {
        return empates;
    }

    public void setEmpates(int empates) {
        this.empates = empates;
    }

    // Método toString (opcional)
    @Override
    public String toString() {
        return "EstadisticasJugador{" +
                "id='" + id + '\'' +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", partidasJugadas=" + partidasJugadas +
                ", partidasGanadas=" + partidasGanadas +
                ", partidasPerdidas=" + partidasPerdidas +
                ", empates=" + empates +
                '}';
    }
}

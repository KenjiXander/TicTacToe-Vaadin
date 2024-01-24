package com.example.application.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collation = "Player")

public class Player {
    @Id
    private String id;
    private String ganadas = "";
    private String perdidas = "";
    private String empatadas = "";

    //El unque es para que no se vuelva a repetir y sea unico
    @Indexed(name="nombre",unique = true)
    private String nombre = "";
    private String contrasena = "";

    public Player(){
    }

    public Player(String id, String ganadas, String perdidas, String empatadas, String nombre) {
        this.id = id;
        this.ganadas = ganadas;
        this.perdidas = perdidas;
        this.empatadas = empatadas;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGanadas() {
        return ganadas;
    }

    public void setGanadas(String ganadas) {
        this.ganadas = ganadas;
    }

    public String getPerdidas() {
        return perdidas;
    }

    public void setPerdidas(String perdidas) {
        this.perdidas = perdidas;
    }

    public String getEmpatadas() {
        return empatadas;
    }

    public void setEmpatadas(String empatadas) {
        this.empatadas = empatadas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

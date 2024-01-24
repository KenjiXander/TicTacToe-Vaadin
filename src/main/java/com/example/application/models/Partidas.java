package com.example.application.models;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Partidas {

    @Id
    private String PTotals = "";

    @Indexed
    private String jugador1;
    private String jugador2;

    public Partidas(){
    }

    public Partidas(String players, String PTotals) {
        this.PTotals = players;
        this.PTotals = PTotals;
    }

    public String getPTotals() {
        return PTotals;
    }

    public void setPTotals(String PTotals) {
        this.PTotals = PTotals;
    }

    public String getJugador1() {
        return jugador1;
    }

    public void setJugador1(String jugador1) {
        this.jugador1 = jugador1;
    }

    public String getJugador2() {
        return jugador2;
    }

    public void setJugador2(String jugador2) {
        this.jugador2 = jugador2;
    }
}


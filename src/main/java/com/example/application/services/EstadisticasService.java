package com.example.application.services;

import com.example.application.EstadisticasJugador;

import java.util.HashMap;
import java.util.Map;

public class EstadisticasService {
    private static final Map<String, EstadisticasJugador> estadisticas = new HashMap<>();

    public static void actualizarEstadisticas(String nombreJugador, boolean esGanador, boolean esEmpate) {
        EstadisticasJugador jugador = estadisticas.getOrDefault(nombreJugador, new EstadisticasJugador(nombreJugador, 0, 0, 0, 0));
        jugador.setPartidasJugadas(jugador.getPartidasJugadas() + 1);
        if (esGanador) {
            jugador.setPartidasGanadas(jugador.getPartidasGanadas() + 1);
        } else if (esEmpate) {
            jugador.setEmpates(jugador.getEmpates() + 1);
        } else {
            jugador.setPartidasPerdidas(jugador.getPartidasPerdidas() + 1);
        }
        estadisticas.put(nombreJugador, jugador);
    }

    public static Map<String, EstadisticasJugador> obtenerEstadisticas() {
        return estadisticas;
    }
}

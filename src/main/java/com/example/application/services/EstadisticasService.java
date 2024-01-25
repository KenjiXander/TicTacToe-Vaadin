package com.example.application.services;

import com.example.application.models.EstadisticasJugador;
import com.example.application.repositories.EstadisticasJugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadisticasService {

    private final EstadisticasJugadorRepository estadisticasJugadorRepository;

    @Autowired
    public EstadisticasService(EstadisticasJugadorRepository estadisticasJugadorRepository) {
        this.estadisticasJugadorRepository = estadisticasJugadorRepository;
    }

    public void actualizarEstadisticas(String nombreJugador, boolean esGanador, boolean esEmpate) {
        EstadisticasJugador jugador = estadisticasJugadorRepository.findByNombreUsuario(nombreJugador);
        if (jugador == null) {
            jugador = new EstadisticasJugador(nombreJugador, 0, 0, 0, 0);
        }

        jugador.setPartidasJugadas(jugador.getPartidasJugadas() + 1);
        if (esGanador) {
            jugador.setPartidasGanadas(jugador.getPartidasGanadas() + 1);
        } else if (esEmpate) {
            jugador.setEmpates(jugador.getEmpates() + 1);
        } else {
            jugador.setPartidasPerdidas(jugador.getPartidasPerdidas() + 1);
        }

        estadisticasJugadorRepository.save(jugador);
    }

    public List<EstadisticasJugador> obtenerTodasLasEstadisticas() {
        return estadisticasJugadorRepository.findAll();
    }
}

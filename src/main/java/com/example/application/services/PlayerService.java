package com.example.application.services;

import com.example.application.models.Player;
import com.example.application.services.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {

    private PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> listaPlayers() {
        List<Player> listaPlayers = new ArrayList<>();
        try {
            listaPlayers = playerRepository.findAll();
        } catch (Exception ex) {
            System.out.println("Fallo al conectar con la Base de Datos");
        }
        return listaPlayers;
    }

    public Player obtenerPorNombre(String nombre) {
        Player player = null;
        try {
            player = playerRepository.findByNombre(nombre);
        } catch (Exception ex) {
            System.out.println("No se encontró un jugador con ese nombre");
        }
        return player;
    }

    public void agregarPlayer(Player player) {
        try {
            playerRepository.save(player);
        } catch (Exception ex) {
            System.out.println("No se logró agregar jugador");
        }
    }

    public void borrarPlayer(String nombre) {
        try {
            Player player = playerRepository.findByNombre(nombre);
            playerRepository.delete(player);
            System.out.println("Jugador borrado");
        } catch (Exception ex) {
            System.out.println("Error al borrar jugador");
        }
    }
}

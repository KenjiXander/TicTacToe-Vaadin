package com.example.application.services;

import com.example.application.models.Player;

import java.util.ArrayList;
import java.util.List;

public class PartidasService {

    private PartidasRepository partidasRepository;

    public PartidasService(PartidasRepository partidasRepository){
        this.partidasRepository = partidasRepository;
    }

    public List<Player> listaPartidas(){
        List<Player> listaPartidas = new ArrayList<>();
        try{
            listaPartidas = partidasRepository.findAll();
        }catch (Exception ex){
            System.out.println("Fallo al conectar con la Base de Datos");
        }
        return listaPartidas;
    }

    public Player obtenerPorNombre(String nombre){
        Player partidas = null;
        try{
            partidas = partidasRepository.findByNombre(nombre);
        }catch (Exception ex){
            System.out.println("No se encontró una partida con ese nombre");
        }
        return partidas;
    }
}

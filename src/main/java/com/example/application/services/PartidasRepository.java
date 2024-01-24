package com.example.application.services;

import com.example.application.models.Partidas;
import com.example.application.models.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartidasRepository extends MongoRepository<Player, String> {
    Player findByNombre(String nombre);
}
package com.example.application.services;

import com.example.application.models.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidasRepository extends MongoRepository<Player, String> {
    Player findByNombre(String nombre);
}
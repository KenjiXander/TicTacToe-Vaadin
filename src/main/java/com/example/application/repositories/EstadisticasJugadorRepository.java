package com.example.application.repositories;

import com.example.application.models.EstadisticasJugador;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadisticasJugadorRepository extends MongoRepository<EstadisticasJugador, String> {
    EstadisticasJugador findByNombreUsuario(String nombreUsuario);
}

package com.Vetta.voiture_service.Repository;

import com.Vetta.voiture_service.model.Voiture;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VoitureRepository extends MongoRepository<Voiture,String> {

    Optional<Voiture> findVoitureById(String id);
}

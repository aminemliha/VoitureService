package com.Vetta.voiture_service.service;

import com.Vetta.voiture_service.Repository.VoitureRepository;
import com.Vetta.voiture_service.dto.VoitureRequest;
import com.Vetta.voiture_service.dto.VoitureResponse;
import com.Vetta.voiture_service.model.Voiture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class VoitureService {

    private  final VoitureRepository voitureRepository;

    @Autowired
    public VoitureService(VoitureRepository voitureRepository) {
        this.voitureRepository = voitureRepository;
    }


    public void createVoiture(VoitureRequest voitureRequest){
        Voiture voiture = Voiture.builder()
                .name(voitureRequest.getName())
                .description(voitureRequest.getDescription())
                .price(voitureRequest.getPrice())
                .build();
        voitureRepository.save(voiture);
        log.info("Voiture {} is saved",voiture.getId());
    }

    public List<VoitureResponse> getAllVoitures() {
        List<Voiture> voitures =  voitureRepository.findAll();
        return voitures.stream().map(this::mapToVoitureResponse).toList();
    }

    public VoitureResponse getVoitureById(String id) {
        Voiture voiture = voitureRepository.findVoitureById(id)
                .orElseThrow(() -> new IllegalArgumentException("Voiture not found with id: " + id));
        return mapToVoitureResponse(voiture);
    }

    private VoitureResponse mapToVoitureResponse(Voiture voiture) {
        return  VoitureResponse.builder()
                .id(voiture.getId())
                .name(voiture.getName())
                .description(voiture.getDescription())
                .price(voiture.getPrice())
                .build();
    }





    public void deleteVoiture(String id) {
        if (voitureRepository.existsById(id)) {
            voitureRepository.deleteById(id);
            log.info("Voiture with id {} has been deleted", id);
        } else {
            log.error("Voiture with id {} not found", id);
            throw new IllegalArgumentException("Voiture not found with id: " + id);
        }
    }

    public void updateVoiture(String id, VoitureRequest voitureRequest) {
        // Vérifiez si la voiture existe
        Voiture existingVoiture = voitureRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Voiture not found with id: " + id));

        // Mettre à jour les champs
        existingVoiture.setName(voitureRequest.getName());
        existingVoiture.setDescription(voitureRequest.getDescription());
        existingVoiture.setPrice(voitureRequest.getPrice());

        // Sauvegarder les modifications
        voitureRepository.save(existingVoiture);
        log.info("Voiture with id {} has been updated", id);
    }




}

package com.Vetta.voiture_service.controller;

import com.Vetta.voiture_service.dto.VoitureRequest;
import com.Vetta.voiture_service.dto.VoitureResponse;
import com.Vetta.voiture_service.service.VoitureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voiture")
@RequiredArgsConstructor
public class VoitureController {

    private final VoitureService voitureService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createVoiture(@RequestBody VoitureRequest voitureRequest){
        voitureService.createVoiture(voitureRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VoitureResponse> getAllVoitures(){
        return voitureService.getAllVoitures();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoitureResponse> getVoitureById(@PathVariable String id) {
        try {
            VoitureResponse voiture = voitureService.getVoitureById(id);
            return ResponseEntity.ok(voiture);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVoiture(@PathVariable String id) {
        try {
            voitureService.deleteVoiture(id); // Appelle le service
            return ResponseEntity.ok("Voiture deleted successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }



    @PutMapping("/{id}")
    public ResponseEntity<String> updateVoiture(@PathVariable String id, @RequestBody VoitureRequest voitureRequest) {
        try {
            voitureService.updateVoiture(id, voitureRequest); // Appelle la méthode updateVoiture du service
            return ResponseEntity.ok("Voiture updated successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

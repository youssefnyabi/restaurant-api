package com.restaurant.web;

import com.restaurant.dao.PlatRepository;
import com.restaurant.exception.PlatNotFoundException;
import com.restaurant.model.Plat;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/plats")
public class PlatController {

    private final PlatRepository platRepository;

    public PlatController(PlatRepository platRepository) {
        this.platRepository = platRepository;
    }

    @GetMapping
    public ResponseEntity<List<Plat>> getAllPlats() {
        return ResponseEntity.ok(platRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plat> getPlatById(@PathVariable Long id) {
        Plat plat = platRepository.findById(id)
                .orElseThrow(() -> new PlatNotFoundException(id));
        return ResponseEntity.ok(plat);
    }

    @PostMapping
    public ResponseEntity<Plat> createPlat(@Valid @RequestBody Plat plat) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(platRepository.save(plat));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plat> updatePlat(
            @PathVariable Long id, @Valid @RequestBody Plat details) {

        Plat plat = platRepository.findById(id)
                .orElseThrow(() -> new PlatNotFoundException(id));

        plat.setNom(details.getNom());
        plat.setCalories(details.getCalories());
        plat.setPrix(details.getPrix());
        plat.setCoutIngredients(details.getCoutIngredients());

        return ResponseEntity.ok(platRepository.save(plat));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlat(@PathVariable Long id) {
        Plat plat = platRepository.findById(id)
                .orElseThrow(() -> new PlatNotFoundException(id));

        platRepository.delete(plat);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/healthy")
    public ResponseEntity<List<Plat>> getHealthyPlats() {
        return ResponseEntity.ok(
                platRepository.findByCaloriesLessThan(500)
        );
    }
}
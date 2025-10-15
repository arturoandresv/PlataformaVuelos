package edu.unimagdalena.itineraryservice.controller;

import edu.unimagdalena.itineraryservice.dto.UserItineraryDTO;
import edu.unimagdalena.itineraryservice.service.UserItineraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/itinerary/v1/user-itineraries")
@RequiredArgsConstructor
public class UserItineraryController {

    private final UserItineraryService userItineraryService;

    @PostMapping
    public ResponseEntity<UserItineraryDTO> create(@RequestBody UserItineraryDTO request) {
        UserItineraryDTO saved = userItineraryService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserItineraryDTO> getById(@PathVariable String id) {
        UserItineraryDTO found = userItineraryService.findById(id);
        return found != null ? ResponseEntity.ok(found) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        boolean deleted = userItineraryService.deleteById(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

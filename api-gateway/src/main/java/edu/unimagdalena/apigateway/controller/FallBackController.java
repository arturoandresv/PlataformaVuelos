package edu.unimagdalena.apigateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/fallback")
public class FallBackController {

    @GetMapping("/itinerary")
    public ResponseEntity<Map<String, Object>> itineraryFallback() {
        return ResponseEntity.status(503).body(Map.of(
                "message", "Itinerary service unavailable, please retry later",
                "status", 503
        ));
    }

    @GetMapping("/booking")
    public ResponseEntity<Map<String, Object>> bookingFallback() {
        return ResponseEntity.status(503).body(Map.of(
                "message", "Booking service unavailable, please retry later",
                "status", 503
        ));
    }
}

package edu.unimagdalena.apigateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {

    @GetMapping("/fallback/activity")
    public ResponseEntity<String> activityFallback() {
        return ResponseEntity.ok("El servicio de actividades no está disponible en este momento.");
    }
}

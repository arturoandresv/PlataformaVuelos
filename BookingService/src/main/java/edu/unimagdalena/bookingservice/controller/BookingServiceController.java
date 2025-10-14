package edu.unimagdalena.bookingservice.controller;

import edu.unimagdalena.bookingservice.dto.CreateReservationRequest;
import edu.unimagdalena.bookingservice.dto.Money;
import edu.unimagdalena.bookingservice.dto.ReservationDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/booking/v1/reservations")
public class BookingServiceController {

    @PostMapping
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody CreateReservationRequest request) {
        ReservationDTO reservation = new ReservationDTO(
                "RES-12345",
                "CONFIRMED",
                "HTL-98765",
                new Money("USD", 299.99)
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(reservation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservation(@PathVariable String id) {

        return Map.of(
                {}
        );
    }



}

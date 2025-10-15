package edu.unimagdalena.bookingservice.controller;

import edu.unimagdalena.bookingservice.dto.CreateReservationRequest;
import edu.unimagdalena.bookingservice.dto.ReservationDTO;
import edu.unimagdalena.bookingservice.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/booking/v1/reservations")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<ReservationDTO> create(@RequestBody CreateReservationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.createReservation(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getById(@PathVariable String id) {
        ReservationDTO found = bookingService.findById(id);
        return found != null ? ResponseEntity.ok(found) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancel(@PathVariable String id) {
        boolean cancelled = bookingService.cancelReservation(id);
        return cancelled ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

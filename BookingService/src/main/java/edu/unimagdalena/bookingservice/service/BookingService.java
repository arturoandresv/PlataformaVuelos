package edu.unimagdalena.bookingservice.service;

import edu.unimagdalena.bookingservice.dto.CreateReservationRequest;
import edu.unimagdalena.bookingservice.dto.Money;
import edu.unimagdalena.bookingservice.dto.ReservationDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BookingService {

    private final Map<String, ReservationDTO> storage = new ConcurrentHashMap<>();
    private final Random random = new Random();

    public ReservationDTO createReservation(CreateReservationRequest request) {

        String reservationId = "RES-" + (9001000 + random.nextInt(900));

        String hotelConfirmation = "H-" + UUID.randomUUID().toString().substring(0, 5).toUpperCase();

        Money total = new Money("USD", BigDecimal.valueOf(1890.50));

        ReservationDTO reservation = new ReservationDTO(
                reservationId,
                "CONFIRMED",
                hotelConfirmation,
                total
        );

        storage.put(reservationId, reservation);
        return reservation;
    }

    public ReservationDTO findById(String id) {
        return storage.get(id);
    }

    public boolean cancelReservation(String id) {
        ReservationDTO existing = storage.get(id);
        if (existing == null) return false;

        ReservationDTO cancelled = new ReservationDTO(
                existing.reservationId(),
                "CANCELLED",
                existing.hotelConfirmation(),
                existing.total()
        );

        storage.put(id, cancelled);
        return true;
    }
}
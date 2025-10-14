package edu.unimagdalena.bookingservice.dto;

public record ReservationDTO(String reservationId,
                             String status,
                             String hotelConfirmation,
                             Money total) {
}

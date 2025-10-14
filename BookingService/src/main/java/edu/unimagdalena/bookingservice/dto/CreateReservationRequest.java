package edu.unimagdalena.bookingservice.dto;

import java.util.List;

public record CreateReservationRequest(String itineraryId,
                                       FlightRef flightRef,
                                       List<Guest> guests,
                                       Payment payment,
                                       Contact contact) {
}

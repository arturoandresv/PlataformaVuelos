package edu.unimagdalena.itineraryservice.dto;

public record UserItineraryDTO(
        String id,
        String userId,
        String itineraryId,
        String origin,
        String destination,
        String departDate,
        String returnDate,
        int adults,
        int children,
        int rooms
) {}

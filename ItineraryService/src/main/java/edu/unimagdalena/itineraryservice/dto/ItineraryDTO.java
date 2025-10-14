package edu.unimagdalena.itineraryservice.dto;

public record ItineraryDTO(String id, Money price, FlightInfo flight, HotelInfo hotel) {
}

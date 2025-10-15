package edu.unimagdalena.itineraryservice.dto;

import java.time.LocalDate;

public record HotelInfo(String name, LocalDate checkIn, LocalDate checkOut, String roomType, Double rating) {
}

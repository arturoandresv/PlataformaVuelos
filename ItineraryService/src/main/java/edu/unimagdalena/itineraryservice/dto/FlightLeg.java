package edu.unimagdalena.itineraryservice.dto;

import java.time.OffsetDateTime;

public record FlightLeg(String carrier, String number, OffsetDateTime depart, OffsetDateTime arrive, Integer stops) {
}

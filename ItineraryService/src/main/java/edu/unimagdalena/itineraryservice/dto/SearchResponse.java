package edu.unimagdalena.itineraryservice.dto;

import java.util.List;

public record SearchResponse(List<ItineraryDTO> itineraries, String searchId) {
}

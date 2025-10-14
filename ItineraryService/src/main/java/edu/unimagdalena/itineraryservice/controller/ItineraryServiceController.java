package edu.unimagdalena.itineraryservice.controller;

import edu.unimagdalena.itineraryservice.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/itinerary/v1")
public class ItineraryServiceController {

    @GetMapping(value = "/search", params = {"origin","destination","departDate","returnDate","adults","rooms"})
    public ResponseEntity<SearchResponse> getItinerary(
            @RequestParam String origin,
            @RequestParam String destination,
            @RequestParam String departDate,
            @RequestParam String returnDate,
            @RequestParam int adults,
            @RequestParam int rooms
    ) {
        Money price = new Money("USD", BigDecimal.valueOf(820.50));

        FlightLeg outbound = new FlightLeg("AA", "123", OffsetDateTime.now(), OffsetDateTime.now().plusHours(5), 0);
        FlightLeg inbound = new FlightLeg("AA", "124", OffsetDateTime.now().plusDays(7), OffsetDateTime.now().plusDays(7).plusHours(5), 0);
        FlightInfo flightInfo = new FlightInfo(outbound, inbound);

        HotelInfo hotelInfo = new HotelInfo("Holiday Inn", LocalDate.now(), LocalDate.now().plusDays(7), "Suite", 4.5);

        ItineraryDTO itinerary = new ItineraryDTO(UUID.randomUUID().toString(), price, flightInfo, hotelInfo);
        SearchResponse response = new SearchResponse(List.of(itinerary), UUID.randomUUID().toString());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/details/{itineraryId}")
    public ResponseEntity<ItineraryDTO> getDetails(@PathVariable String itineraryId) {
        Money price = new Money("USD", BigDecimal.valueOf(650.00));
        FlightLeg outbound = new FlightLeg("LATAM", "901", OffsetDateTime.now(), OffsetDateTime.now().plusHours(6), 1);
        FlightInfo flightInfo = new FlightInfo(outbound, null);
        HotelInfo hotel = new HotelInfo("NH Bogotá", LocalDate.now(), LocalDate.now().plusDays(5), "Deluxe", 4.7);

        ItineraryDTO itinerary = new ItineraryDTO(itineraryId, price, flightInfo, hotel);
        return ResponseEntity.ok(itinerary);
    }

}

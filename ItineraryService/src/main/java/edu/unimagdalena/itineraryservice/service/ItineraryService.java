package edu.unimagdalena.itineraryservice.service;

import edu.unimagdalena.itineraryservice.dto.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ItineraryService {

    @Cacheable(
            value = "searchCache",
            key = "#origin + '-' + #destination + '-' + #departDate + '-' + #returnDate + '-' + #adults + '-' + #children + '-' + #rooms"
    )
    public SearchResponse getItinerary(String origin, String destination, String departDate, String returnDate, Integer adults, Integer children, Integer rooms) {

        ItineraryDTO itinerary = new ItineraryDTO(
                "ITI-" + origin + "-" + destination + "-" + departDate.replace("-", "") + "-" + returnDate.replace("-", "") + "-001",
                new Money("USD", BigDecimal.valueOf(1890.50)),
                new FlightInfo(
                        new FlightLeg("IB", "IB6584",
                                OffsetDateTime.parse("2025-12-10T21:10:00-05:00"),
                                OffsetDateTime.parse("2025-12-11T12:10:00+01:00"), 0),
                        new FlightLeg("IB", "IB6583",
                                OffsetDateTime.parse("2025-12-20T15:30:00+01:00"),
                                OffsetDateTime.parse("2025-12-20T20:30:00-05:00"), 0)
                ),
                new HotelInfo(
                        "Hotel Gran Vía Madrid",
                        LocalDate.parse("2025-12-11"),
                        LocalDate.parse("2025-12-20"),
                        "Doble + extra",
                        4.5
                )
        );

        return new SearchResponse(
                List.of(itinerary),
                "SRCH-9c2b"
        );
    }

    public ItineraryDTO getDetails(String id) {
        return new ItineraryDTO(
                "ITI-BOG-MAD-20251210-20251220-001",
                new Money("USD", BigDecimal.valueOf(1890.50)),
                new FlightInfo(
                        new FlightLeg("IB", "IB6584",
                                OffsetDateTime.parse("2025-12-10T21:10:00-05:00"),
                                OffsetDateTime.parse("2025-12-11T12:10:00+01:00"), 0),
                        new FlightLeg("IB", "IB6583",
                                OffsetDateTime.parse("2025-12-20T15:30:00+01:00"),
                                OffsetDateTime.parse("2025-12-20T20:30:00-05:00"), 0)
                ),
                new HotelInfo(
                        "Hotel Gran Vía Madrid",
                        LocalDate.parse("2025-12-11"),
                        LocalDate.parse("2025-12-20"),
                        "Doble + extra",
                        4.5
                ));
    }
}

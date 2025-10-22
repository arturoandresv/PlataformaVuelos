package edu.unimagdalena.itineraryservice.controller;

import edu.unimagdalena.itineraryservice.dto.*;
import edu.unimagdalena.itineraryservice.service.ItineraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/itinerary/v1")
@RequiredArgsConstructor
public class ItineraryController {

    private final ItineraryService itineraryService;

    @GetMapping(value = "/search")
    public SearchResponse getItinerary(
            @RequestParam String origin,
            @RequestParam String destination,
            @RequestParam String departDate,
            @RequestParam String returnDate,
            @RequestParam Integer adults,
            @RequestParam Integer children,
            @RequestParam Integer rooms
    ) {
        return itineraryService.getItinerary(origin, destination, departDate, returnDate, adults, children, rooms) ;
    }

    @GetMapping("/details/{itineraryId}")
    public ItineraryDTO getDetails(@PathVariable String itineraryId) {
        return itineraryService.getDetails(itineraryId);
    }
}

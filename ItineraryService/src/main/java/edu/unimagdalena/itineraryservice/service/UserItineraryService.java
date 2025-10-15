package edu.unimagdalena.itineraryservice.service;

import edu.unimagdalena.itineraryservice.dto.UserItineraryDTO;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserItineraryService {

    private final Map<String, UserItineraryDTO> storage = new ConcurrentHashMap<>();

    public UserItineraryDTO save(UserItineraryDTO request) {
        String id = "USR-ITI-" + UUID.randomUUID().toString().substring(0, 8);
        UserItineraryDTO saved = new UserItineraryDTO(
                id,
                request.userId(),
                request.itineraryId(),
                request.origin(),
                request.destination(),
                request.departDate(),
                request.returnDate(),
                request.adults(),
                request.children(),
                request.rooms()
        );
        storage.put(id, saved);
        return saved;
    }

    public UserItineraryDTO findById(String id) {
        return storage.get(id);
    }

    public boolean deleteById(String id) {
        return storage.remove(id) != null;
    }
}

package edu.unimagdalena.itineraryservice.dto;

import java.math.BigDecimal;

public record Money(String currency, BigDecimal amount) {
}

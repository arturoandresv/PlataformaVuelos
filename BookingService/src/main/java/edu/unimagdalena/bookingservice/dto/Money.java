package edu.unimagdalena.bookingservice.dto;

import java.math.BigDecimal;

public record Money(String currency, BigDecimal amount) {
}

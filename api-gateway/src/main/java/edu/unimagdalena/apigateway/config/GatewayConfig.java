package edu.unimagdalena.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
        return builder.routes() // Ruta para el microservicio de itirenario
                .route("itinerary -service", r -> r
                        .path("/itinerary/*")              // Cuando el cliente llama a /itinerary/*
                        .filters(f -> f.stripPrefix(1))    // Quita "/itinerary" del path antes de enviar
                        .uri("lb://itinerary-service"))     // URI del microservicio productos
                // Ruta para el microservicio de reservas
                .route("booking -service", r -> r
                        .path("/booking/*")                // Cuando el cliente llama a /booking/*
                        .filters(f -> f.stripPrefix(1))    // Quita "/booking" del path
                        .uri("lb://booking-service"))     // URI del microservicio órdenes
                .build();
    }
}

package edu.unimagdalena.apigateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Component
public class LoggingFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String id = UUID.randomUUID().toString();
        log.info("[{}] Request: {} {}", id,
                exchange.getRequest().getMethod(), exchange.getRequest().getURI());
        return chain.filter(exchange)
                .doOnSuccess(done -> log.info("[{}] Response: {}", id,
                        exchange.getResponse().getStatusCode()));
    }
}

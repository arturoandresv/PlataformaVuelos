package edu.unimagdalena.apigateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class ResponseMaskingFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            List<String> sensitiveHeaders = List.of("Authorization", "Payment-Token");
            sensitiveHeaders.forEach(h -> exchange.getResponse().getHeaders().remove(h));
        }));
    }
}


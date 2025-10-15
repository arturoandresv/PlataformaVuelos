package edu.unimagdalena.apigateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class CacheFilter implements GlobalFilter {

    private final ReactiveStringRedisTemplate redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        // Solo GET /search
        if (!request.getMethod().equals(HttpMethod.GET) ||
                !request.getURI().getPath().contains("/itinerary/search")) {
            return chain.filter(exchange);
        }

        String cacheKey = "search:" + request.getURI().getQuery();

        return redisTemplate.opsForValue().get(cacheKey)
                .flatMap(cachedBody -> {
                    ServerHttpResponse response = exchange.getResponse();
                    response.getHeaders().add("X-Cache", "HIT");
                    byte[] bytes = cachedBody.getBytes(StandardCharsets.UTF_8);
                    DataBuffer buffer = response.bufferFactory().wrap(bytes);
                    return response.writeWith(Mono.just(buffer));
                })
                .switchIfEmpty(Mono.defer(() -> {
                    // Cache MISS → continuar y almacenar respuesta
                    return chain.filter(exchange.mutate().response(new CachingServerHttpResponseDecorator(
                            exchange.getResponse(), redisTemplate, cacheKey)).build());
                }));
    }
}

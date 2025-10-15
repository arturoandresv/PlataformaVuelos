package edu.unimagdalena.apigateway.config;

import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class CachingServerHttpResponseDecorator extends ServerHttpResponseDecorator {
    private final ReactiveStringRedisTemplate redisTemplate;
    private final String cacheKey;

    public CachingServerHttpResponseDecorator(ServerHttpResponse delegate,
                                              ReactiveStringRedisTemplate redisTemplate,
                                              String cacheKey) {
        super(delegate);
        this.redisTemplate = redisTemplate;
        this.cacheKey = cacheKey;
    }

    @Override
    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
        return super.writeWith(Flux.from(body).doOnNext(buffer -> {
            byte[] content = new byte[buffer.readableByteCount()];
            buffer.read(content);
            redisTemplate.opsForValue()
                    .set(cacheKey, new String(content, StandardCharsets.UTF_8), Duration.ofSeconds(45))
                    .subscribe();
        }));
    }
}

package com.mymsa.gateway.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {

    public GlobalFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String sessionID = RandomStringUtils.randomAlphabetic(8);
            ServerHttpRequest request = exchange.getRequest().mutate()
                    .header("sessionID", sessionID)
                    .build();

            log.info("{} | gatway-begin|Y|src={}, uri={}, ms=", sessionID, exchange.getRequest().getRemoteAddress(), request.getURI());

            return chain.filter(exchange.mutate().request(request).build())
                    .then(Mono.fromRunnable(() -> {
                        int code = exchange.getResponse().getStatusCode().value();
                        boolean result = code == 200;
                        log.info("{}|gateway-end|{}|code={}", sessionID, result ? "Y" : "N", code);
                    }));
        };
    }

    @Data
    public static class Config {}
}

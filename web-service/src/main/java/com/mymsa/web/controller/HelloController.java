package com.mymsa.web.controller;

import com.mymsa.core.context.MSAContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequestMapping("/web-service")
@RestController
@Slf4j
public class HelloController {

    @GetMapping("/hello")
    public Mono<String> hello(@RequestHeader(value="sessionID", required = false) String sessionID) {
        MSAContext context = new MSAContext(sessionID);
        log.info("{}|hello", context);

        return Mono.just("hello : " + System.currentTimeMillis())
                .doOnSuccess(result -> {
                    log.error("{}|hello-request|Y|{}", context, result);
                })
                .doOnError(error -> {
                   log.info("{}|hello-request|N|", context, error);
                });
    }
}

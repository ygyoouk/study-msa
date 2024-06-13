package com.mymsa.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequestMapping("/app-web")
@RestController
@Slf4j
public class HelloController {

    @GetMapping("/hello")
    public Mono<String> hello() {
        log.info("hello controller - /app-web/hello !!!");
        return Mono.just("hello : " + System.currentTimeMillis());
    }
}

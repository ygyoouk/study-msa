package com.mymsa.web.controller;

import com.mymsa.core.context.MSAContext;
import com.mymsa.web.model.BlogPost;
import com.mymsa.web.service.BlogPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/web-service")
@RestController
@Slf4j
@RequiredArgsConstructor
public class BlogPostController {

    private final BlogPostService blogPostService;

    @PostMapping("/blog-post")
    public Mono<BlogPost> create(@RequestHeader(value = "sessionId", required = false) String sessionId, @RequestBody BlogPost blogPost) {
        MSAContext context = new MSAContext(sessionId);

        return blogPostService.create(context, blogPost)
                .doOnSuccess(result -> {
                    log.info("{} | create-blog-post | Y | {}", context, result);
                })
                .doOnError(error -> {
                    log.error("{} | create-blog-post | ", context, error);
                });
    }

    @GetMapping("/blog-post")
    public Flux<BlogPost> findAll(@RequestHeader(value = "sessionId", required = false) String sessionId) {
        MSAContext context = new MSAContext(sessionId);
        return blogPostService.findAll(context)
                .doOnComplete(() -> {
                    log.info("{} | find-all-blog-post | ", context);
                })
                .doOnError(error -> {
                    log.error("{} | find-all-blog-post | ", context, error);
                });
    }

    @PutMapping("/blog-post")
    public Mono<BlogPost> update(@RequestHeader(value = "sessionID", required = false) String sessionID, @RequestBody BlogPost blogPost) {
        MSAContext context = new MSAContext(sessionID);
        log.info("{}|update-blog-post||{}", context, blogPost);
        return blogPostService.update(context, blogPost)
                .doOnSuccess(result -> {
                    log.info("{}|update-blog-post|Y|{}", context, result);
                })
                .doOnError(error -> {
                    log.error("{}|update-blog-post|N|", context, error);
                });
    }

    @DeleteMapping("/blog-post/{post_id}")
    public Mono<BlogPost> deleteById(@RequestHeader(value = "sessionID", required = false) String sessionID, @PathVariable String post_id) {
        MSAContext context = new MSAContext(sessionID);
        log.info("{}|delete-blog-post||{}", context, post_id);
        return blogPostService.deleteById(context, post_id)
                .doOnSuccess(result -> {
                    log.info("{}|delete-blog-post|Y|{}", context, result);
                })
                .doOnError(error -> {
                    log.error("{}|delete-blog-post|N|", context, error);
                });
    }

}

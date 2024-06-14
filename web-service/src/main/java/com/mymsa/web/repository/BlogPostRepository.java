package com.mymsa.web.repository;

import com.mymsa.core.context.MSAContext;
import com.mymsa.web.model.BlogPost;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Repository
@RequiredArgsConstructor
public class BlogPostRepository {

    private final ReactiveMongoTemplate mongoTemplate;

    public Mono<BlogPost> create(MSAContext context, BlogPost blogPost) {
        blogPost.setCreate_time(new Date());
        return mongoTemplate.save(blogPost);
    }

    public Flux<BlogPost> findAll(MSAContext context) {
        return mongoTemplate.findAll(BlogPost.class);
    }

    public Mono<BlogPost> findById(MSAContext context, String id) {
        Query query = Query.query(Criteria.where("post_id").is(id));
        return mongoTemplate.findById(query, BlogPost.class);
    }

    public Mono<BlogPost> update(MSAContext context, BlogPost blogPost) {
        Query query = Query.query(Criteria.where("post_id").is(blogPost.getPost_id()));
        Update update = Update.update("title", blogPost.getTitle())
                .set("content", blogPost.getContent());

        return mongoTemplate.findAndModify(query, update, BlogPost.class)
                .switchIfEmpty(Mono.error(new Exception("Update failed > " + blogPost.getPost_id())));
    }

    public Mono<BlogPost> deleteById(MSAContext context, String id) {
        Query query = Query.query(Criteria.where("post_id").is(id));
        return mongoTemplate.findAndRemove(query, BlogPost.class);
    }
}

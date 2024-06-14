package com.mymsa.web.service;

import com.mymsa.core.context.MSAContext;
import com.mymsa.web.model.BlogPost;
import com.mymsa.web.repository.BlogPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;

    public Mono<BlogPost> create(MSAContext msaContext, BlogPost blogPost){
        return blogPostRepository.create(msaContext, blogPost);
    }

    public Flux<BlogPost> findAll(MSAContext msaContext){
        return blogPostRepository.findAll(msaContext);
    }

    public Mono<BlogPost> findById(MSAContext msaContext, String id){
        return blogPostRepository.findById(msaContext, id);
    }

    public Mono<BlogPost> update(MSAContext msaContext, BlogPost blogPost){
        return blogPostRepository.update(msaContext, blogPost);
    }

    public Mono<BlogPost> deleteById(MSAContext msaContext, String id){
        return blogPostRepository.deleteById(msaContext, id);
    }
}

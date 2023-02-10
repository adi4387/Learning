package com.reactivespring.handler;

import com.reactivespring.domain.Review;
import com.reactivespring.repository.MovieReviewsRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ReviewsHandler {

    private MovieReviewsRepository movieReviewsRepository;

    public static Mono<ServerResponse> addReview(final ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Review.class)
                .flatMap(MovieReviewsRepository::save)
                .;
    }
}

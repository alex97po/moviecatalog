package com.pogorelov.moviecatalog.client;

import com.pogorelov.moviecatalog.domain.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("movie-info-service")
public interface MovieInfoClient {

    @GetMapping("/movies/{movieId}")
    Movie getMovieInfo(@PathVariable String movieId);
}

package com.pogorelov.moviecatalog.web;

import com.pogorelov.moviecatalog.domain.CatalogItem;
import com.pogorelov.moviecatalog.domain.Movie;
import com.pogorelov.moviecatalog.domain.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalogForUser(@PathVariable String userId) {

        List<Rating> ratings = List.of(
                new Rating("1234", 4),
                new Rating("5678", 3)
        );

        return ratings.stream()
                .map(rating -> {
                    Movie movie =restTemplate.getForObject("http://localhost:8081/movies/" + rating.getMovieId(), Movie.class);
                    return new CatalogItem(movie.getName(), "desc", rating.getRating());
                })
                .collect(Collectors.toList());
    }
}

package com.pogorelov.moviecatalog.web;

import com.pogorelov.moviecatalog.client.MovieInfoClient;
import com.pogorelov.moviecatalog.domain.CatalogItem;
import com.pogorelov.moviecatalog.domain.Movie;
import com.pogorelov.moviecatalog.domain.Rating;
import com.pogorelov.moviecatalog.web.vm.RatingVM;
import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MovieInfoClient movieInfoClient;

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalogForUser(@PathVariable String userId) {

        RatingVM userRatings = restTemplate.getForObject("http://rating-data-service/ratings/users/" + userId, RatingVM.class);
        List<Rating> ratings = userRatings.getRatings();

        return ratings.stream()
                .map(rating -> {
                    Movie movie = movieInfoClient.getMovieInfo(rating.getMovieId());
                    //comment
                    return new CatalogItem(movie.getName(), "desc", rating.getRating());
                })
                .collect(Collectors.toList());
    }
}

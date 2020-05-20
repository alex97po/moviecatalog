package com.pogorelov.moviecatalog.web;

import com.pogorelov.moviecatalog.domain.CatalogItem;
import com.pogorelov.moviecatalog.domain.Rating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalogForUser(@PathVariable String userId) {
        List<Rating> ratings = List.of(
                new Rating("1234", 4),
                new Rating("5678", 3)
        );

        return ratings.stream()
                .map(rating -> new CatalogItem("Transformer", "Test", 4))
                .collect(Collectors.toList());
    }
}

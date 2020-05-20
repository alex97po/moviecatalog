package com.pogorelov.moviecatalog.web;

import com.pogorelov.moviecatalog.domain.CatalogItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalogForUser(@PathVariable String userId) {
        return List.of(new CatalogItem("Transformers", "test", 4));
    }
}

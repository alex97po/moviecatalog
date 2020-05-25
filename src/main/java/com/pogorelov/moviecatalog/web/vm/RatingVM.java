package com.pogorelov.moviecatalog.web.vm;

import com.pogorelov.moviecatalog.domain.Rating;
import lombok.Data;

import java.util.List;

@Data
public class RatingVM {

    private List<Rating> ratings;
}

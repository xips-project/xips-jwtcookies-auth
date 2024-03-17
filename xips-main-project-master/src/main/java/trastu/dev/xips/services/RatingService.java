package trastu.dev.xips.services;

import trastu.dev.xips.entities.Rating;

import java.util.List;

public interface RatingService {

    public List<Rating> ratingsByUsername(String username);
}

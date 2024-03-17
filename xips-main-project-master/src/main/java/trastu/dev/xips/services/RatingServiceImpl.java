package trastu.dev.xips.services;

import trastu.dev.xips.entities.Rating;
import trastu.dev.xips.repositories.ProductRepository;

import java.util.List;

public class RatingServiceImpl implements RatingService {

    private final ProductRepository.RatingRepository ratingRepository;

    public RatingServiceImpl(ProductRepository.RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }


    @Override
    public List<Rating> ratingsByUsername(String username) {
        return ratingRepository.findByUsername(username);
    }
}

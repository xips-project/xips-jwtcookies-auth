package trastu.dev.xips.utils;

import org.springframework.stereotype.Component;
import trastu.dev.xips.entities.Rating;

import java.util.List;

@Component
public class AverageRating {

    public double calculateAverageRating(List<Rating> ratings) {
        if (ratings.isEmpty()) {
            return 0;
        }

        double totalRating = ratings.stream()
                .mapToDouble(Rating::getStars)
                .sum();
        return totalRating / ratings.size();
    }
}

package trastu.dev.xips.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import trastu.dev.xips.entities.Product;
import trastu.dev.xips.entities.ProductType;
import trastu.dev.xips.entities.Rating;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByProductType(ProductType productType);

    @Repository
    interface RatingRepository extends MongoRepository<Rating, String> {

        List<Rating> findByUsername(String username);
    }
}

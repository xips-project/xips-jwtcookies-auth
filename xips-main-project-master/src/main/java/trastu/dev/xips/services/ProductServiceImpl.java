package trastu.dev.xips.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import trastu.dev.xips.dto.ProductDTO;
import trastu.dev.xips.entities.Product;
import trastu.dev.xips.entities.ProductType;
import trastu.dev.xips.entities.Rating;
import trastu.dev.xips.entities.User;
import trastu.dev.xips.repositories.ProductRepository;
import trastu.dev.xips.repositories.UserRepository;
import trastu.dev.xips.utils.AverageRating;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByProductType(ProductType productType) {
        return productRepository.findByProductType(productType);
    }

    @Override
    public Product save(ProductDTO productDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Product newProduct = Product.builder()
                .name(productDTO.getName())
                .productType(productDTO.getProductType())
                .username(username)
                .build();
        return productRepository.save(newProduct);
    }

    public List<Map<String, Object>> getProductsAndRatingsByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }

        User user = userOptional.get();
        List<Product> products = productRepository.findByUsername(username);
        AverageRating averageRating = new AverageRating();

        double rating;
        if (user.getRatings() !=null && !user.getRatings().isEmpty()){
            rating = averageRating.calculateAverageRating(user.getRatings());
        } else {
            rating = 0.0;
        }

        return products.stream()
                .map(product -> {
                    return Map.<String, Object>of(
                            "productName", product.getName(),
                            "productType", product.getProductType(),
                            "username", product.getUsername(),
                            "ratings", rating
                    );
                })
                .collect(Collectors.toList());
    }
}

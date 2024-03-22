package trastu.dev.xips.services;

import trastu.dev.xips.dto.ProductDTO;
import trastu.dev.xips.entities.Product;
import trastu.dev.xips.entities.ProductType;

import java.util.List;
import java.util.Map;

public interface ProductService {

    List<Product> getAllProducts();

    List<Product> getProductsByProductType(ProductType productType);

    Product save(ProductDTO productDTO);

    List<Map<String, Object>> getProductsAndRatingsByUsername(String username);
}

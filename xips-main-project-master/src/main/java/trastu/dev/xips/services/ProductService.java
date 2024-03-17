package trastu.dev.xips.services;

import trastu.dev.xips.dto.ProductDTO;
import trastu.dev.xips.entities.Product;
import trastu.dev.xips.entities.ProductType;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    List<Product> getProductsByProductType(ProductType productType);

    Product save(ProductDTO productDTO);
}

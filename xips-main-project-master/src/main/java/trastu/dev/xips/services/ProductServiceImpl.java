package trastu.dev.xips.services;

import org.springframework.stereotype.Service;
import trastu.dev.xips.dto.ProductDTO;
import trastu.dev.xips.entities.Product;
import trastu.dev.xips.entities.ProductType;
import trastu.dev.xips.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
        Product newProduct = Product.builder()
                .name(productDTO.getName())
                .productType(productDTO.getProductType())
                .build();
        return productRepository.save(newProduct);
    }
}

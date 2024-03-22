package trastu.dev.xips.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import trastu.dev.xips.dto.ProductDTO;
import trastu.dev.xips.dto.UserDTO;
import trastu.dev.xips.entities.Country;
import trastu.dev.xips.entities.Product;
import trastu.dev.xips.entities.ProductType;
import trastu.dev.xips.entities.User;
import trastu.dev.xips.services.ProductService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ModelAttribute("types")
    public ProductType[] getTypes() {
        return ProductType.values();
    }

    @GetMapping("/list")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> productList = productService.getAllProducts();
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/list/user/{username}")
    public List<Map<String, Object>> getProductsAndRatingsByUsername(@PathVariable String username) {
        return productService.getProductsAndRatingsByUsername(username);
    }

    @GetMapping("/list/type/{productType}")
    public List<Product> getProductsByProductType(@PathVariable ProductType productType) {
        return productService.getProductsByProductType(productType);
    }

    /* create without form

    @PostMapping("/create")
    public ResponseEntity<Product> newProduct(@RequestBody ProductDTO productDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        Product newProduct = productService.save(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

     */

    @GetMapping("/create")
    public String showForm(Model model){
        ProductDTO productDTO = new ProductDTO();
        model.addAttribute("productDTO", productDTO);
        return "new-product";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute ProductDTO productDTO, Model model){
        Product product = productService.save(productDTO);
        model.addAttribute("product", product);
        return "creation-success";
    }

}

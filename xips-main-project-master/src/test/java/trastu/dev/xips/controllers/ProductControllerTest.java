package trastu.dev.xips.controllers;

import org.h2.util.json.JSONValidationTargetWithoutUniqueKeys;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import trastu.dev.xips.entities.Product;
import trastu.dev.xips.entities.ProductType;
import trastu.dev.xips.repositories.ProductRepository;
import trastu.dev.xips.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@WithMockUser("MockUser")
@AutoConfigureTestDatabase
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductRepository productRepository;

    private final String basePath = "/api/v1/products";
    private static List<Product> products;

    @BeforeAll
    static void setup() {
        Product product1 = Product.builder()
                .id("product1")
                .productType(ProductType.BOOKS)
                .name("book1")
                .username("afcasco")
                .build();

        Product product2 = Product.builder()
                .id("product2")
                .productType(ProductType.FILMS)
                .name("movie1")
                .username("aleixmadrid")
                .build();

        products = List.of(product1,product2);
    }



    @Test
    void getTypes() {
    }
    @Test
    public void getAllProducts() throws Exception {
        when(productRepository.findAll()).thenReturn(products);

        mockMvc.perform(get(basePath + "/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)));
    }

    @Test
    // TODO
    void getProductsAndRatingsByUsername() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get(basePath+"/list/user/afcasco")).andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    void getProductsByProductType() throws Exception {

        when(productRepository.findByProductType(ProductType.BOOKS))
                .thenReturn(List.of(products.get(0)));

        mockMvc.perform(get(basePath + "/list/type/BOOKS")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)));


    }

    @Test
    void showForm() {


    }

    @Test
    void create() {
    }
}
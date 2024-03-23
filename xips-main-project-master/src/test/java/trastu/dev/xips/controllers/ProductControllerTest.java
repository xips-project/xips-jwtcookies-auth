package trastu.dev.xips.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import trastu.dev.xips.entities.Product;
import trastu.dev.xips.entities.ProductType;
import trastu.dev.xips.repositories.ProductRepository;
import trastu.dev.xips.repositories.UserRepository;
import trastu.dev.xips.security.config.ApplicationConfig;
import trastu.dev.xips.security.config.SecurityConfig;
import trastu.dev.xips.security.jwt.JWTService;
import trastu.dev.xips.services.ProductService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
@Import({SecurityConfig.class, ApplicationConfig.class})
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductService productService;

    @MockBean
    ProductRepository productRepository;

    @MockBean
    JWTService jwtService;

    @MockBean
    UserRepository userRepository;

    private final String basePath = "/api/v1/products";
    private static List<Product> products;

    public static final SecurityMockMvcRequestPostProcessors.JwtRequestPostProcessor jwtRequestPostProcessor() {

        return jwt().jwt(jwt -> jwt.claims(claims -> {

                })
                .tokenValue("messaging-client")
                .notBefore(Instant.now().minusSeconds(5L)));
    }


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

        products = List.of(product1, product2);
    }


    @Test
    public void shouldGetNoProducts() throws Exception {
        given(productService.getAllProducts()).willReturn(new ArrayList<>());

        mockMvc.perform(get(basePath + "/list")
                        .with(jwtRequestPostProcessor())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }


    @Test
    public void shouldGetTwoProducts() throws Exception {
        given(productService.getAllProducts()).willReturn(products);

        mockMvc.perform(get(basePath + "/list")
                        .with(jwtRequestPostProcessor())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
        // TODO
    void getProductsAndRatingsByUsername() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get(basePath + "/list/user/afcasco")).andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    void shouldReturnOneProductOfTypeBook() throws Exception {

        given(productService.getProductsByProductType(ProductType.BOOKS))
                .willReturn(List.of(products.get(0)));

        mockMvc.perform(get(basePath + "/list/type/" + ProductType.BOOKS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(jwtRequestPostProcessor()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));


    }

    @Test
    void shouldReturnShowFormView() throws Exception {
        mockMvc.perform(get(basePath + "/create")
                        .with(jwtRequestPostProcessor()))
                .andExpect(status().isOk())
                .andExpect(view().name("new-product"));
    }


    @Test
    void shouldReturnCreatedSuccessView() throws Exception {
        mockMvc.perform(post(basePath + "/create")
                        .with(jwtRequestPostProcessor()))
                .andExpect(status().isOk())
                .andExpect(view().name("creation-success"));
    }


}
package trastu.dev.xips.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import trastu.dev.xips.entities.ProductType;
import trastu.dev.xips.entities.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private String name;
    private ProductType productType;
    private String username;

}

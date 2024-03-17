package trastu.dev.xips.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import trastu.dev.xips.entities.ProductType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    @Id
    private String id;
    private String name;
    private ProductType productType;

}

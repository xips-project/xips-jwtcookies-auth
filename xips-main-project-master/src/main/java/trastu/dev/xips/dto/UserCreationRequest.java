package trastu.dev.xips.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreationRequest {
    private String firstname;
    private String lastName;
    private String email;
    private String password;
    private LocalDate birthdate;
    private String address;
    private String cityName;
    private String zipCode;
    private String country;
}

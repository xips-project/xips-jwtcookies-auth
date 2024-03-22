package trastu.dev.xips.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import trastu.dev.xips.entities.Role;
import trastu.dev.xips.entities.UserProfile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private String username;
    private String email;
    private double rating;
    private UserProfile userProfile;


}

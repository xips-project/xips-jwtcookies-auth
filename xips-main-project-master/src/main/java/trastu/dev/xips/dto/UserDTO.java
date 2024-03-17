package trastu.dev.xips.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import trastu.dev.xips.entities.Role;
import trastu.dev.xips.entities.UserProfile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String name;
    private String lastname;
    private Role role;
    private UserProfile userProfile;




}

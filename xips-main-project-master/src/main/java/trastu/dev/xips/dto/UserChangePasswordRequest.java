package trastu.dev.xips.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserChangePasswordRequest {
    private String username;
    private String oldPassword;
    private String newPassword;
}

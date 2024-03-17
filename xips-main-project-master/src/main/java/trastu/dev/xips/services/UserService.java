package trastu.dev.xips.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import trastu.dev.xips.dto.UserDTO;
import trastu.dev.xips.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface UserService {

    List<User> getAll();

    Optional<User> getUserByUsername(String username);

    Optional<User> getOne(UUID id);

    User save(UserDTO userDTO);

    void delete(UUID id);


}

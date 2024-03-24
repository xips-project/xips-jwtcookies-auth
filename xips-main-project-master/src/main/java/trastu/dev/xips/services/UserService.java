package trastu.dev.xips.services;

import trastu.dev.xips.dto.UserDTO;
import trastu.dev.xips.entities.Rating;
import trastu.dev.xips.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface UserService {

    List<User> findAll();

    Optional<User> getUserByUsername(String username);

    Optional<User> getOne(UUID id);

    User save(UserDTO userDTO);

    void delete(UUID id);

    void setRating (Rating rating);


}

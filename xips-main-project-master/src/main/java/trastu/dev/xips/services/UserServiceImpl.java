package trastu.dev.xips.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import trastu.dev.xips.dto.UserDTO;
import trastu.dev.xips.entities.Rating;
import trastu.dev.xips.entities.User;
import trastu.dev.xips.entities.UserProfile;
import trastu.dev.xips.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    @Override
    public Optional<User> getOne(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(UserDTO userDTO) {
        UserProfile userProfile = userDTO.getUserProfile();
        User newUser = User.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .userProfile(userProfile)
                .build();
        userProfile.setUser(newUser);
        userRepository.save(newUser);
        return newUser;
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public void setRating(Rating rating) {
        User user = rating.getUser();
        user.getRatings().add(rating);
        userRepository.save(rating.getUser());
    }


}

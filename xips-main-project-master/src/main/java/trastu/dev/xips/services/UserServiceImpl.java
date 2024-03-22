package trastu.dev.xips.services;

import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import trastu.dev.xips.dto.UserDTO;
import trastu.dev.xips.entities.User;
import trastu.dev.xips.entities.UserProfile;
import trastu.dev.xips.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
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

        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setEmail(userDTO.getEmail());
        newUser.setUserProfile(UserProfile.builder()
                .firstname(userDTO.getUserProfile().getFirstname())
                .lastname(userDTO.getUserProfile().getLastname())
                .address(userDTO.getUserProfile().getAddress())
                .birthdate(userDTO.getUserProfile().getBirthdate())
                .zipCode(userDTO.getUserProfile().getZipCode())
                .cityName(userDTO.getUserProfile().getCityName())
                .country(userDTO.getUserProfile().getCountry())
                .build());
        userRepository.save(newUser);
        return newUser;
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }



}

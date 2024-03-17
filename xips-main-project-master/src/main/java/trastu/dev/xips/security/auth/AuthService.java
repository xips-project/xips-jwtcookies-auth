package trastu.dev.xips.security.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import trastu.dev.xips.entities.Role;
import trastu.dev.xips.entities.User;
import trastu.dev.xips.entities.UserProfile;
import trastu.dev.xips.repositories.UserRepository;
import trastu.dev.xips.security.jwt.JWTService;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;



    public AuthService(UserRepository userRepository, JWTService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username, request.password));
        UserDetails userDetails = userRepository.findByUsername(request.username).orElseThrow();
        String token = jwtService.getToken(userDetails);
        return AuthResponse.builder()
                .token(token)
                .build();

    }

    public AuthResponse register(RegisterRequest request) {

        User user = User.builder()
                .username(request.username)
                .password(passwordEncoder.encode(request.password))
                .email(request.email)
                .role(Role.USER)
                .userProfile(UserProfile.builder()
                        .firstname(request.userProfile.getFirstname())
                        .lastname(request.userProfile.getLastname())
                        .birthdate(request.userProfile.getBirthdate())
                        .address(request.userProfile.getAddress())
                        .cityName(request.userProfile.getCityName())
                        .zipCode(request.userProfile.getZipCode())
                        .country(request.userProfile.getCountry())
                        .build())
                .build();

        userRepository.save(user);
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();

    }
}

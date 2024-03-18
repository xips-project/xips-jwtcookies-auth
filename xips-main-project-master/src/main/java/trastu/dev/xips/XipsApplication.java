package trastu.dev.xips;

import io.jsonwebtoken.security.Password;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import trastu.dev.xips.entities.User;
import trastu.dev.xips.entities.UserProfile;
import trastu.dev.xips.repositories.UserRepository;
import trastu.dev.xips.services.UserService;

@SpringBootApplication
@RequiredArgsConstructor
public class XipsApplication implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(XipsApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {

            User user = User.builder()
                    .username("test")
                    .password(passwordEncoder.encode("test"))
                    .userProfile(new UserProfile())
                    .build();

            userRepository.save(user);
        }
    }
}

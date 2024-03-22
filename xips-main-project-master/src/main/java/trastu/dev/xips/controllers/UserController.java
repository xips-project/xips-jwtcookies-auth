package trastu.dev.xips.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import trastu.dev.xips.dto.UserDTO;
import trastu.dev.xips.entities.Country;
import trastu.dev.xips.entities.User;
import trastu.dev.xips.entities.UserProfile;
import trastu.dev.xips.services.UserServiceImpl;
import trastu.dev.xips.utils.AverageRating;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserServiceImpl userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private final AverageRating averageRating;

    public UserController(UserServiceImpl userService, AverageRating averageRating) {
        this.userService = userService;
        this.averageRating = averageRating;
    }

    @ModelAttribute("countries")
    public Country[] getCountries() {
        return Country.values();

    }

    @GetMapping("/list")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userService.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();

        for (User user : users){
            double rating = averageRating.calculateAverageRating(user.getRatings());

            if (user.getUserProfile() !=null){
                userDTOS.add(UserDTO.builder()
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .rating(rating)
                        .userProfile(UserProfile.builder()
                                .firstname(user.getUserProfile().getFirstname())
                                .build())
                        .build());
            } else {
                userDTOS.add(UserDTO.builder()
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .rating(rating)
                        .build());
            }
        }

        return ResponseEntity.ok(userDTOS);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> newUser(@org.springframework.web.bind.annotation.RequestBody UserDTO userDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        if (userService.getUserByUsername(userDTO.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        try {
            User savedUser = userService.save(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating user: " + e.getMessage());
        }

    }

    @GetMapping("/redirect")
    public String redirect(){

        return "home";
    }

    @PostMapping("/create")
    public String createUser(Model model, @RequestParam String firstname, @RequestParam String lastName, @RequestParam String email, @RequestParam String password){
        /*
        String encryptedPassword = bCryptPasswordEncoder.encode(password);
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType,
                "{\"profile\": {\"firstName\": \"" + firstname + "\",\"lastName\": \"" + lastName + "\",\"email\": \"" + email + "\",\"login\": \"" + email + "\"},\"credentials\": {\"password\": {\"value\": \"" + encryptedPassword + "\"}}}");

        Request request = new Request.Builder()
                .url("https://dev-82475405.okta.com/api/v1/users?activate=false")
                .method("POST", body)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "SSWS 00E7XvYk01A3oILfuVfkyl_XmqhfA1JCtmbnLJfX3r") // Reemplaza tu_token_de_API con tu token real
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        } catch (Exception e){
            e.printStackTrace();
        }

    */
        model.addAttribute("login", email);
        return "success";
    }





    /* Login html form

    @GetMapping("/signup")
    public String showForm(Model model){
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);
        return "registration";
    }

    @PostMapping("/signup")
    public String signUpUser(@ModelAttribute UserDTO userDTO, HttpSession session){
        User user = userService.save(userDTO);
        session.setAttribute("user", user);
        return "signup-success";
    }*/



}

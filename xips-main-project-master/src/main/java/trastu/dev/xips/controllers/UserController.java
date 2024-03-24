package trastu.dev.xips.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import trastu.dev.xips.dto.UserChangePasswordRequest;
import trastu.dev.xips.dto.UserCreationRequest;
import trastu.dev.xips.dto.UserDTO;
import trastu.dev.xips.entities.Country;
import trastu.dev.xips.entities.Rating;
import trastu.dev.xips.entities.User;
import trastu.dev.xips.entities.UserProfile;
import trastu.dev.xips.services.UserServiceImpl;
import trastu.dev.xips.utils.AverageRating;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserServiceImpl userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private final AverageRating averageRating;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final OkHttpClient client = new OkHttpClient();

    public UserController(UserServiceImpl userService, AverageRating averageRating) {
        this.userService = userService;
        this.averageRating = averageRating;
    }

    @ModelAttribute("countries")
    public Country[] getCountries() {
        return Country.values();

    }

    @GetMapping("/list/{limit}")
    public ResponseEntity<String> getAllUsers(@PathVariable int limit) {

        String url = "https://dev-82475405.okta.com//api/v1/users?limit="+limit;

        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "SSWS 00E7XvYk01A3oILfuVfkyl_XmqhfA1JCtmbnLJfX3r")
                .build();
        try (Response response = client.newCall(request).execute()) {
            return ResponseEntity.ok(response.body().string());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during user creation.");
        }
    }

    @GetMapping("/redirect")
    public String redirect(){

        return "";
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@org.springframework.web.bind.annotation.RequestBody UserCreationRequest userCreationRequest){
        try {
            Response response = createUserInOkta(userCreationRequest);
            UserDTO userDTO = createUserDTO(userCreationRequest);
            userService.save(userDTO);
            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during user creation.");
        }
    }

    @PostMapping("/activate")
    public ResponseEntity<String> activateUser(@org.springframework.web.bind.annotation.RequestBody String requestBody){
        try {
            JsonNode jsonNode = objectMapper.readTree(requestBody);

            String username = jsonNode.get("username").asText();

            String url = "https://dev-82475405.okta.com/api/v1/users/" + username + "/lifecycle/activate?sendEmail=true";

            return getStringResponseEntity(url);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error during Json deserialization");
        }
    }

    @PostMapping("/deactivate")
    public ResponseEntity<String> deactivateUser(@org.springframework.web.bind.annotation.RequestBody String requestBody){
        try {
            JsonNode jsonNode = objectMapper.readTree(requestBody);

            String username = jsonNode.get("username").asText();

            String url = "https://dev-82475405.okta.com/api/v1/users/" + username + "/lifecycle/deactivate";

            return getStringResponseEntity(url);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error during Json deserialization");
        }
    }

    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@org.springframework.web.bind.annotation.RequestBody UserChangePasswordRequest requestBody) {

        String username = requestBody.getUsername();
        String url = "https://dev-82475405.okta.com/api/v1/users/" + username + "/credentials/change_password";

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n  \"oldPassword\": { \"value\": \""+requestBody.getOldPassword()+"\" },\n  \"newPassword\": { \"value\": \""+requestBody.getNewPassword()+"\" }\n}");
        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "SSWS 00E7XvYk01A3oILfuVfkyl_XmqhfA1JCtmbnLJfX3r")
                .build();
        try (Response response = client.newCall(request).execute();) {
            return ResponseEntity.ok().body(response.body().string());
        } catch (IOException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during server request");
        }




    }

    private Response createUserInOkta(UserCreationRequest userCreationRequest) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType,
                "{\"profile\": {\"firstName\": \""+ userCreationRequest.getFirstname()
                        + "\",\"lastName\": \"" + userCreationRequest.getLastName()
                        + "\",\"email\": \"" + userCreationRequest.getEmail()
                        + "\",\"login\": \"" + userCreationRequest.getEmail()
                        + "\"},\"credentials\": {\"password\": {\"value\": \"" + userCreationRequest.getPassword() + "\"}}}");

        Request request = new Request.Builder()
                .url("https://dev-82475405.okta.com/api/v1/users?activate=false")
                .method("POST", body)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "SSWS 00E7XvYk01A3oILfuVfkyl_XmqhfA1JCtmbnLJfX3r")
                .build();

        return client.newCall(request).execute();
    }

    private UserDTO createUserDTO(UserCreationRequest userCreationRequest) {

        UserProfile userProfile = UserProfile.builder()
                .firstname(userCreationRequest.getFirstname())
                .lastname(userCreationRequest.getLastName())
                .birthdate(userCreationRequest.getBirthdate())
                .address(userCreationRequest.getAddress())
                .cityName(userCreationRequest.getCityName())
                .zipCode(userCreationRequest.getZipCode())
                .country(userCreationRequest.getCountry())
                .build();

        User user = User.builder()
                .username(userCreationRequest.getEmail())
                .userProfile(userProfile)
                .build();

        userProfile.setUser(user);

        UserDTO userDTO = UserDTO.builder()
                .username(userCreationRequest.getEmail())
                .email(userCreationRequest.getEmail())
                .userProfile(userProfile)
                .build();

        return userDTO;
    }

    @NotNull
    private ResponseEntity<String> getStringResponseEntity(String url) {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "");

        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "SSWS 00E7XvYk01A3oILfuVfkyl_XmqhfA1JCtmbnLJfX3r")
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            return ResponseEntity.ok().body(responseBody);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during user activation/deactivation");
        }
    }

    @PutMapping("/{username}/rating")
    private ResponseEntity<String> setRating(@PathVariable String username, @org.springframework.web.bind.annotation.RequestBody Rating ratingRequest){

        Optional<User> optionalUser = userService.getUserByUsername(username);

        if (optionalUser.isEmpty()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        User user = optionalUser.get();

        Rating rating = Rating.builder()
                .user(user)
                .stars(ratingRequest.getStars())
                .message(ratingRequest.getMessage())
                .build();

        userService.setRating(rating);

        return ResponseEntity.ok().body(rating.toString());
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

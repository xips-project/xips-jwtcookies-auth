package trastu.dev.xips.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import trastu.dev.xips.dto.UserDTO;
import trastu.dev.xips.entities.Country;
import trastu.dev.xips.entities.User;
import trastu.dev.xips.entities.UserProfile;
import trastu.dev.xips.services.UserService;

import javax.naming.Binding;
import java.util.List;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("countries")
    public Country[] getCountries() {
        return Country.values();
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> showUserList() {
        List<User> userList = userService.getAll();
        return ResponseEntity.ok(userList);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> newUser(@RequestBody UserDTO userDTO, BindingResult bindingResult) {

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

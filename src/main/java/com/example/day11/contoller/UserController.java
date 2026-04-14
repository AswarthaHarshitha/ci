package com.example.day11.contoller;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.day11.dto.UserCreateRequest;
import com.example.day11.exception.ResourceNotFoundException;
import com.example.day11.model.User;
import com.example.day11.service.UserService;
import java.util.List;
//import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/users") //request mapping  is used to map the incoming HTTP requests to the specific handler methods in the controller. It can be applied at the class level to specify a common base URL for all methods in the controller, and it can also be applied at the method level to specify more specific URL patterns for individual handler methods.
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }
    @GetMapping("/")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }
//    @GetMapping("/{id}")
//     public String getUserById(@PathVariable int id) {
//     Optional<User> user = service.getUserById(id);
//     if (user.isPresent()){
//         return ResponseEntity.ok(user.get());
//     } else {
//         throw new ResourceNotFoundException("User with id " + id + " not found");
//     }
//     return user.map(u -> "Email: " + u.getEmail() + ", Password: " + u.getPassword()).orElse("User not found");
// }
@GetMapping("/{id}")
public ResponseEntity<User> getUserById(@PathVariable int id) {

    Optional<User> user = service.getUserById(id);

    if (user.isPresent()) {
        return ResponseEntity.ok(user.get());
    } else {
        throw new ResourceNotFoundException("User with id " + id + " not found");
    }
}
    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody UserCreateRequest request) {
        User user = service.createUser(request.getEmail(), request.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id , @RequestBody User user) {
        // Implementation for updating a user
        return service.updateUser(id, user);
        //url: http://localhost:8080/users/1
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        service.deleteUser(id);
        return "User with id " + id + " has been deleted.";
    }
}

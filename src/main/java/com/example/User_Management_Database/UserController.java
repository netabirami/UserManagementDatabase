package com.example.User_Management_Database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers(@RequestParam(required = false) String startsWith) {
        if (startsWith == null) {
            var userEntities = userRepository.findAll();
            var users = new ArrayList<User>();
            for (UserEntity userEntity : userEntities) {
                users.add(new User(
                        userEntity.getUserId(),
                        userEntity.getUserName()
                ));
            }
            return users;
        } else {
            var userEntities = userRepository.findByUserNameStartingWith(startsWith);
            var users = new ArrayList<User>();
            for (UserEntity userEntity : userEntities) {
                users.add(new User(
                        userEntity.getUserId(),
                        userEntity.getUserName()
                ));
            }
            return users;
        }
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Integer id) {
        var userEntity = userRepository.findById(id).get();
        // select * from users where id = id
        return new User(userEntity.getUserId(), userEntity.getUserName());
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        UserEntity newUser = new UserEntity(
                user.getUserId(),
                user.getUserName()
        );
        userRepository.save(newUser);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted successfully.");
    }
}

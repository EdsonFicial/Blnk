package pavulla.firstapi.blnk.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pavulla.firstapi.blnk.dto.CreateUserDTO;
import pavulla.firstapi.blnk.dto.UserResponseDTO;
import pavulla.firstapi.blnk.models.UserEntity;
import pavulla.firstapi.blnk.repository.UserRepository;

import pavulla.firstapi.blnk.Service.ServiceImpl.UserServiceImpl;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/blnk")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    private UserServiceImpl userService;


    

    public UserController(UserRepository userRepository, UserServiceImpl userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("users")
    public List<UserEntity> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();

        if (users.isEmpty()) {
        throw new RuntimeException("No users have been found");
        }
        return users;
    }
    
    @GetMapping("/users/{id}")
    public UserEntity getUserByID(@PathVariable String id) {
        return userRepository.findById(id).get();
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponseDTO> CreateUser(@RequestBody CreateUserDTO user) {
        
        // CreateUserDTO dto = new CreateUserDTO();
        // dto.setName(user.getName());
        // dto.setEmail(user.getEmail());
        // dto.setPassword(user.getPassword());
        // dto.setBalance(user.getbalance());

        UserResponseDTO createdUser = userService.CreateUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/users/{id}")
    public UserEntity updateUser(@PathVariable String id, @RequestBody UserEntity user) {
        UserEntity existingUser = userRepository.findById(id).get();
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        
        return userRepository.save(existingUser);
    }

    @DeleteMapping("/users{id}")
    public String deleteUser(@PathVariable String id) {
        try {
            userRepository.deleteById(id);
            
            return "user deleted successfully";
        } catch (Exception e) {
            
            return "user not found"+ e.getMessage();
        }
    } 
    
    
}

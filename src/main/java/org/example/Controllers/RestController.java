package org.example.Controllers;

import lombok.AllArgsConstructor;

import org.example.DTO.AuthRequestDTO;
import org.example.DTO.JwtResponseDTO;
import org.example.Model.User;
import org.example.Services.JwtService;
import org.example.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
    @Autowired
    UserService userService;
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping("/accessDenied")
    public ResponseEntity<String> accessDenied(){
        return new ResponseEntity<>("ACCESS DENIED!, LOG IN AS ADMIN",HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/login")
    public JwtResponseDTO login(@RequestBody AuthRequestDTO authRequestDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(),authRequestDTO.getPassword()));
        if(authentication.isAuthenticated()){
            return new JwtResponseDTO(jwtService.generateToken(authRequestDTO.getUsername()));
        }else{
            throw new UsernameNotFoundException("Invalid User Request!!");
        }
    }

    @PostMapping("/saveUser")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") String username){

       return ResponseEntity.ok(userService.findUser(username));
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> listUsers(){

        return ResponseEntity.ok(userService.listUsers());
    }
}

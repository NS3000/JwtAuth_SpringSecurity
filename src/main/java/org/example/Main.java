package org.example;

import org.example.Model.User;
import org.example.Model.UserRole;
import org.example.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {

        SpringApplication.run(Main.class);

        }

    @Override
    public void run(String... args) throws Exception {
//        Set<UserRole> list = new HashSet<>();
//        User user = new User(0,"user2","$2a$10$0hH73EqyDcdrC08Nk1WOve5P80MfkQ86m01fjTh8Ks.Eg4Qgi2GJG","Test User2","testuser2@gmail.com",list);
//        list.add(new UserRole(0,"USER"));
//        userRepository.save(user);
    }
}
//FLOW  req->securityFilterChain->jwtAuthFilter->if token present->verify->toController
//                                              ->else->forward to nextFilter(USernameePasswordAuthenticationFilter)->ToController  ->Authenticate incoming user and pass-> if success generate and return token
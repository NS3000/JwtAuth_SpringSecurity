package org.example.Services;

import org.example.Model.User;
import org.example.Model.UserRole;
import org.example.Repository.UserRepository;
import org.example.config.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        org.example.Model.User user = userRepository.findByusername(username).orElseThrow(()-> new UsernameNotFoundException("user name not Found"));
        return org.springframework.security.core.userdetails.User.withUsername(user.getUsername()).password(user.getPassword()).build();
    }

    @Override
    public User findUser(String username) {
        User user = userRepository.findByusername(username).orElseThrow(()-> new UsernameNotFoundException("User Name Not Found"));
        return user;
    }

    public User addUser(User user){
        user.setPassword(WebSecurityConfig.bCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);

    }

    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }
}

package org.example.Services;

import org.example.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public User findUser(String username);
    public User addUser(User user);
    public List<User> listUsers();
}

package com.lld.BookMyShow.services;

import com.lld.BookMyShow.exceptions.UserException;
import com.lld.BookMyShow.models.User;
import com.lld.BookMyShow.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User signUp(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if(userOptional.isPresent()) {
            throw new UserException("User is already registered");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setCreatedAt(new Date());
        user.setBookings(new ArrayList<>());
        return userRepository.save(user);
    }
}

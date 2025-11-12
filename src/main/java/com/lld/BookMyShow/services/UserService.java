package com.lld.BookMyShow.services;

import com.lld.BookMyShow.models.User;


public interface UserService {
    User signUp(String username, String password);
    User login(String username, String password);
}

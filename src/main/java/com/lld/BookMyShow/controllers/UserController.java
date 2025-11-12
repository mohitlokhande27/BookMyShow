package com.lld.BookMyShow.controllers;

import com.lld.BookMyShow.dtos.*;
import com.lld.BookMyShow.models.User;
import com.lld.BookMyShow.services.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private final UserService userService;

//    @Autowired // constructor injection
    // till springboot 3.6 service is autowired, after that controller can be autowired in BookMyShowApplication
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public SignUpResponseDto signup(SignUpRequestDto signUpRequestDto) {
        SignUpResponseDto signUpResponseDto = new SignUpResponseDto();
        try {
            User user = userService.signUp(signUpRequestDto.getUsername(), signUpRequestDto.getPassword());
            signUpResponseDto.setUserId(user.getId());
            signUpResponseDto.setStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            signUpResponseDto.setStatus(ResponseStatus.FAILURE);
            signUpResponseDto.setFailureMessage(e.getMessage());
        }
        return signUpResponseDto;
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        try {
            User user = userService.login(loginRequestDto.getUsername(), loginRequestDto.getPassword());
            loginResponseDto.setLoggedIn(true);
            loginResponseDto.setStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            loginResponseDto.setStatus(ResponseStatus.FAILURE);
            loginResponseDto.setFailureMessage(e.getMessage());
            loginResponseDto.setLoggedIn(false);
        }
        return loginResponseDto;
    }
}

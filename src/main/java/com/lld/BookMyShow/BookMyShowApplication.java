package com.lld.BookMyShow;

import com.lld.BookMyShow.controllers.UserController;
import com.lld.BookMyShow.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookMyShowApplication implements CommandLineRunner {

    @Autowired // field injection, this is not recommended, constructor injection is recommended
    UserController userController;

	public static void main(String[] args) {
		SpringApplication.run(BookMyShowApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
//        SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
//        signUpRequestDto.setUsername("test2@gmail.com");
//        signUpRequestDto.setPassword("testpass1234");
//
//        SignUpResponseDto signUpResponseDto = userController.signup(signUpRequestDto);
//        if(signUpResponseDto.getStatus().equals(ResponseStatus.SUCCESS)) {
//            System.out.println("User created successfully");
//        } else {
//            System.out.println("User creation failed " + signUpResponseDto.getFailureMessage());
//        }

        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setUsername("test2@gmail.com");
        loginRequestDto.setPassword("testpass1234");

        LoginResponseDto loginResponseDto = userController.login(loginRequestDto);
        if(loginResponseDto.getStatus().equals(ResponseStatus.SUCCESS)) {
            System.out.println("User login successfully");
        } else {
            System.out.println("User login failed " + loginResponseDto.getFailureMessage());
        }
    }
}

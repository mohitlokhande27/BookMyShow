package com.lld.BookMyShow;

import com.lld.BookMyShow.controllers.UserController;
import com.lld.BookMyShow.dtos.ResponseStatus;
import com.lld.BookMyShow.dtos.SignUpRequestDto;
import com.lld.BookMyShow.dtos.SignUpResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookMyShowApplication implements CommandLineRunner {

    @Autowired // field injection, this is not recommended, constructor injection is recommended
    UserController userController;

	public static void main(String[] args) {
		SpringApplication.run(BookMyShowApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
        signUpRequestDto.setUsername("test1@gmail.com");
        signUpRequestDto.setPassword("testpass");

        SignUpResponseDto signUpResponseDto = userController.signup(signUpRequestDto);
        if(signUpResponseDto.getStatus().equals(ResponseStatus.SUCCESS)) {
            System.out.println("User created successfully");
        } else {
            System.out.println("User creation failed " + signUpResponseDto.getFailureMessage());
        }
    }
}

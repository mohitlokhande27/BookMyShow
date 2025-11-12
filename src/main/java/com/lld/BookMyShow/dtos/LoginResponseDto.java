package com.lld.BookMyShow.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {
    private ResponseStatus status;
    private String failureMessage;
    private boolean isLoggedIn;
}

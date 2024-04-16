package com.zionique.productserviceapplication.clients.authenticationClient.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateTokenResponseDto {

    private UserDto userDto;
    private SessionStatus sessionStatus;
}

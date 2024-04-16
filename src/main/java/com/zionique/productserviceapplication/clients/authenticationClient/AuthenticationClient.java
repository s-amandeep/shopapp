package com.zionique.productserviceapplication.clients.authenticationClient;

import com.zionique.productserviceapplication.clients.authenticationClient.dtos.ValidateTokenRequestDto;
import com.zionique.productserviceapplication.clients.authenticationClient.dtos.ValidateTokenResponseDto;
import com.zionique.productserviceapplication.clients.fakeStoreApi.FakeStoreProductDto;
import com.zionique.productserviceapplication.models.Product;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class AuthenticationClient {

    private RestTemplateBuilder restTemplateBuilder;
    private static final String BASEURI = "http://localhost:9090/auth/validate";

    public ValidateTokenResponseDto validate(String token, Long userId) {

        RestTemplate restTemplate = restTemplateBuilder.build();

        ValidateTokenRequestDto requestDto = new ValidateTokenRequestDto();
        requestDto.setToken(token);
        requestDto.setUserId(userId);

        ResponseEntity<ValidateTokenResponseDto> response = restTemplate.postForEntity(
                BASEURI, requestDto, ValidateTokenResponseDto.class);

        return response.getBody();
    }
}

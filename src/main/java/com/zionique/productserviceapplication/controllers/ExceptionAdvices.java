package com.zionique.productserviceapplication.controllers;

import com.zionique.productserviceapplication.dtos.ErrorResponseDto;
import com.zionique.productserviceapplication.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvices {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> errorResponse(Exception e){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setMessage(e.getMessage());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }

}

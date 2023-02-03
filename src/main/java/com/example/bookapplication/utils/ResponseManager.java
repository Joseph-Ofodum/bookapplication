package com.example.bookapplication.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResponseManager<T> {
    public ApiResponse<T> success(T data){

        return new ApiResponse<>("Request Successful", true, data);

    }
}

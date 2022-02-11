package com.example.addresssvc.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ErrorMessage {
    private String errorCode;
    private String errorMessage;
    private List<String> details;

}

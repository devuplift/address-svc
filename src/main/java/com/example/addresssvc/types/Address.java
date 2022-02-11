package com.example.addresssvc.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Address {

    @NotBlank(message = "Street cannot be empty")
    private String street;

    @NotBlank(message = "city cannot be empty")
    private String city;

    @NotBlank(message = "state cannot be empty")
    private String state;

    @NotBlank(message = "zip cannot be empty")
    private String zipcode;

}

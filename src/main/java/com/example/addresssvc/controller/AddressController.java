package com.example.addresssvc.controller;

import com.example.addresssvc.service.AddressService;
import com.example.addresssvc.types.Address;
import com.example.addresssvc.validator.StreetValidation;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeoutException;

@RestController
@Slf4j
public class AddressController {

    private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping(value = "/addresses" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Address> getAddresses() {
        log.info("Entered into method");
        return addressService.getAddresses();
    }

    @GetMapping(value = "/addresses/address" , produces = MediaType.APPLICATION_JSON_VALUE)
    public Address getAddress(@StreetValidation @RequestParam String city) {
        return addressService.getAddress(city);
    }

    @DeleteMapping(value = "/addresses/address"  , consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteAddress(@Valid @RequestBody Address address) {
        addressService.deleteAddress(address);
    }

    @PutMapping(value = "/addresses/address"  , consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateAddress(@Valid @RequestBody Address address) {
        addressService.updateAddress(address);
    }

    @PostMapping(value = "/addresses/address"  , consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveAddress(@Valid @RequestBody Address address) {
        addressService.saveAddress(address);
    }

}

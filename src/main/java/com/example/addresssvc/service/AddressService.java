package com.example.addresssvc.service;


import com.example.addresssvc.types.Address;

import java.util.List;

public interface AddressService {
    List<Address> getAddresses();
    Address getAddress(String city);
    void deleteAddress(Address address);
    void updateAddress(Address address);
    void saveAddress(Address address);

}

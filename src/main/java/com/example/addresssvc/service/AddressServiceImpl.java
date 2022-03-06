package com.example.addresssvc.service;

import com.example.addresssvc.Entities.AddressEntity;
import com.example.addresssvc.repository.AddressRepository;
import com.example.addresssvc.types.Address;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static net.logstash.logback.argument.StructuredArguments.v;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @RateLimiter(name = "serviceB#ratelimiter")
    @Transactional
    @Override
    public List<Address> getAddresses() {
        List<Address> addressList = new ArrayList<>();
        List<AddressEntity>  entityList = addressRepository.findAll();
        for (AddressEntity entity : entityList) {
            Address address = new Address();
            address.setCity(entity.getCity());
            address.setZipcode(entity.getZipcode());
            address.setState(entity.getState());
            address.setStreet(entity.getStreet());
            addressList.add(address);
        }

        if(addressList.isEmpty()) {
            log.error("No address found");
        }

       return addressList;
    }


    @Retry(name = "serviceB#retry")
    @CircuitBreaker(name = "serviceB#circuitbreaker")
    @Transactional
    @Override
    public Address getAddress(String city) {
        log.info("Enter into getAddress ");
        log.info("searching city  {}-> {}.", v("city", city),v("city2", city));
        Address address = new Address();
        AddressEntity entity = addressRepository.getByCity(city);
        if(null != entity) {
            address.setCity(entity.getCity());
            address.setZipcode(entity.getZipcode());
            address.setState(entity.getState());
            address.setStreet(entity.getStreet());
        }
        if(null != address && null == address.getCity()) {
            log.error("No address found , empty city");
        }
        //return address;
       throw  new RuntimeException();
    }

    @Transactional
    @Override
    public void deleteAddress(Address address) {

        AddressEntity entity = addressRepository.getByCity(address.getCity());
        if(null != entity) {
            addressRepository.delete(entity);
        }


    }

    @Transactional
    @Override
    public void updateAddress(Address address) {

        AddressEntity entity = addressRepository.getByCity(address.getCity());
        if( null != entity) {
            entity.setCity(address.getCity());
            entity.setState(address.getState());
            entity.setZipcode(address.getZipcode());
            entity.setState(address.getState());

            addressRepository.save(entity);
        }

    }

    @Transactional
    @Override
    public void saveAddress(Address address) {

        AddressEntity entity = new AddressEntity();
        entity.setCity(address.getCity());
        entity.setState(address.getState());
        entity.setZipcode(address.getZipcode());
        entity.setStreet(address.getStreet());

        addressRepository.save(entity);

    }
}

package com.example.addresssvc.repository;

import com.example.addresssvc.Entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    AddressEntity getByCity(String city);
}

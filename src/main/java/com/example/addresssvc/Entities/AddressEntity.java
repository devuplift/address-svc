package com.example.addresssvc.Entities;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Address")
@Data
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String street;
    private String city;

    @Column(unique = true)
    private String state;

    @Column(name = "ZIP")
    private String zipcode;

}

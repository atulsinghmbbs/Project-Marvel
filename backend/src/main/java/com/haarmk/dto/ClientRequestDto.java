package com.haarmk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    
    
//    private String phoneNumber;
//    private String streetName;
//    private String city;
//    private String state;
//    private String homeNumber;
//    private String zipCode;
}

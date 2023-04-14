package com.haarmk.dto;


import com.haarmk.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
//    private String phoneNumber;
//    private String streetName;
//    private String city;
//    private String state;
//    private String homeNumber;
//    private String zipCode;

    public ClientResponseDto(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
//        this.phoneNumber = user.getPhoneNumber();
//        this.streetName = user.getStreetName();
//        this.city = user.getCity();
//        this.state = user.getState();
//        this.homeNumber = user.getHomeNumber();
//        this.zipCode = user.getZipCode();
    }
}

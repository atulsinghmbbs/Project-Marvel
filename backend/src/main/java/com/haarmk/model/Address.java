/**
 * 
 */
package com.haarmk.model;

import java.time.OffsetDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * @author indicate0
 *
 */
@Data
@Entity
public class Address {
    @Id    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String phone;
    private String fax;
    private String email;
    private String organization;
    private String gstin;
    private String postalCode;
    @CreationTimestamp
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",nullable = false, updatable = false, insertable = false)
    private OffsetDateTime createdAt;
    //@ManyToOne(cascade = CascadeType.ALL)
    @Embedded
    private Country country;

    
    
    
}
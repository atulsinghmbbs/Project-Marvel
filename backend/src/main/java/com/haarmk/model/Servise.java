/**
 * 
 */
package com.haarmk.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

/**
 * @author Ankit Patel
 *
 */

@Entity
public class Servise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Product product;
    @ManyToOne
    private ServiseStaus status;
    @ManyToMany
    private List<User> users = new ArrayList<>();
}

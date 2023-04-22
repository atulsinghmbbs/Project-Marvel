package com.haarmk.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(unique = true,nullable = false)
	private String username;
	private String firstName;
	private String lastName;
	@Column(unique = true,nullable = false)
    private String email;
    private String image;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @ManyToMany(cascade = CascadeType.ALL)
    @Builder.Default
    private Set<Authority> authorities = new HashSet<>();
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;  
    @OneToOne(mappedBy = "user", cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private Address addresses;;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH})
    @Builder.Default
    private List<Feedback> feedbacks = new ArrayList<>();
    @OneToOne(mappedBy = "user", cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @Builder.Default
    private Cart cart = new Cart();
    @ManyToMany(mappedBy = "users", cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @Builder.Default
    private List<Servise> servises = new ArrayList<>();
    
    
}

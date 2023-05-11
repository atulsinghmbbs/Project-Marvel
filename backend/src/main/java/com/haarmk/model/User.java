package com.haarmk.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
public class User implements UserDetails{
	
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
    @ManyToMany(fetch = FetchType.EAGER)
    @Builder.Default
    private Set<Authority> authorities = new HashSet<>();
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    
    private AuthProvider provider;

    private String providerId;
//	@CreationTimestamp
//	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",nullable = false, updatable = false, insertable = false)
//	private OffsetDateTime createdAt;
//    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
//    private Address addresses;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH})
    @Builder.Default
    @JsonIgnore
    private List<Feedback> feedbacks = new ArrayList<>();
    @OneToOne(mappedBy = "user", cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.DETACH})
    @Builder.Default
    @JsonIgnore
    private Cart cart = new Cart();
    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @Builder.Default
    @JsonIgnore
    private Set<Orders> orders = new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @Builder.Default
    @JsonIgnore
    private List<Services> servises = new ArrayList<>();
	

    
    
}

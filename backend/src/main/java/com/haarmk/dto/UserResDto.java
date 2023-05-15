package com.haarmk.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.haarmk.model.Authority;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResDto {
    private Long id;
	private String username;
	private String firstName;
	private String lastName;
    private String email;
    private String image;
    private List<Authority> authorities;
    private boolean enabled;
    
	public UserResDto(Long id, String username, String firstName, String lastName, String email, String image,
			boolean enabled) {
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.image = image;
		this.enabled = enabled;
	}
}

package com.haarmk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.haarmk.config.filter.JwtValidatorFilter;
import com.haarmk.service.JwtAuthenticationProvider;
import com.haarmk.service.UserServiceImpl;
import com.haarmk.service.interfaces.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private JwtAuthenticationProvider jwtAuthenticationProvider;
	@Autowired
	private JwtValidatorFilter jwtValidatorFilter;
	

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         	http
        		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        		.and()
        		.csrf().disable()
                .headers().frameOptions().sameOrigin()
                .and()
                .authorizeHttpRequests(auth->{
                    auth
                        .requestMatchers("/","/login","/signup","/h2-console/").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**","/swagger-ui.html").permitAll()
                        .requestMatchers("/secure").authenticated()
                        .requestMatchers("/home").permitAll()
                        
                        .anyRequest().permitAll();

                })
                .addFilterBefore(jwtValidatorFilter, UsernamePasswordAuthenticationFilter.class);
              
         	return http.build();
    }

 
    @Bean
    public AuthenticationManager authenticationManager(UserServiceImpl userService) {
    	return new ProviderManager(jwtAuthenticationProvider);
	}
    


}

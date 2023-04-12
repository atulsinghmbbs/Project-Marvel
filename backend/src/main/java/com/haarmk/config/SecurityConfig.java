package com.haarmk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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
                        .requestMatchers("/","/login","/signup").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**","/swagger-ui.html").permitAll()
                        .requestMatchers("/haarmk/secure").authenticated()
                        .requestMatchers("/haarmk/home").permitAll()
                        
                        .anyRequest().permitAll();

                })
                .addFilterBefore(jwtValidatorFilter, UsernamePasswordAuthenticationFilter.class);
              
         	return http.build();
    }

 
    @Bean
    public AuthenticationManager authenticationManager(UserServiceImpl userService) {
    	return new ProviderManager(jwtAuthenticationProvider);
	}
    

    @Bean
    public FilterRegistrationBean corsFilter() {
    	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    	
    	CorsConfiguration corsConn = new CorsConfiguration();
    	
    	corsConn.setAllowCredentials(true);
    	corsConn.addAllowedOriginPattern("*");
    	corsConn.addAllowedHeader("Authorization");
    	corsConn.addAllowedHeader("Content-Type");
    	corsConn.addAllowedHeader("Accept");
    	corsConn.addAllowedMethod("POST");
    	corsConn.addAllowedMethod("GET");
    	corsConn.addAllowedMethod("DELETE");
    	corsConn.addAllowedMethod("PUT");
    	corsConn.addAllowedMethod("OPTIONS");
    	corsConn.setMaxAge(3600L);
    	
    	source.registerCorsConfiguration("/**", corsConn);
    	
    	FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
    	
    	return bean;
    	
    }

}

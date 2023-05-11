package com.haarmk.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.haarmk.filter.JwtValidatorFilter;
import com.haarmk.security.RestAuthenticationEntryPoint;
import com.haarmk.security.oauth2.CustomOAuth2UserService;
import com.haarmk.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import com.haarmk.security.oauth2.OAuth2AuthenticationFailureHandler;
import com.haarmk.security.oauth2.OAuth2AuthenticationSuccessHandler;
import com.haarmk.service.JwtAuthenticationProvider;


@Configuration 
@EnableWebSecurity(debug = false)
public class SecurityConfig {
	@Autowired private JwtAuthenticationProvider jwtAuthenticationProvider;
	@Autowired private JwtValidatorFilter jwtValidatorFilter;

	
//	@Autowired OAuth2LoginAuthenticationProvider oAuth2LoginAuthenticationProvider;

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Autowired private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @Autowired private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    @Autowired private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;


    


    /*
      By default, Spring OAuth2 uses HttpSessionOAuth2AuthorizationRequestRepository to save
      the authorization request. But, since our service is stateless, we can't save it in
      the session. We'll save the request in a Base64 encoded cookie instead.
    */
//    @Bean
//    public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
//        return new HttpCookieOAuth2AuthorizationRequestRepository();
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         	http
         	.oauth2Login(Customizer.withDefaults())
         	.cors(Customizer.withDefaults())
         	.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//         	.cors().disable()
            .and()
        		.csrf().disable()
                .headers().frameOptions().sameOrigin().and()
                .formLogin()
                .disable()
            .httpBasic()
                .disable()
            .exceptionHandling()
                .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .and()
                
                .authorizeHttpRequests(auth->{
                    auth
                        .requestMatchers("/","/auth/login","/auth/signup").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**","/swagger-ui.html", "/api-docs").permitAll()
                        .requestMatchers("/secure").authenticated()
                        .requestMatchers("/home","/domains/search").permitAll()
                        .requestMatchers(HttpMethod.GET, "/feedbacks/**", "/feedback").permitAll()
                        .requestMatchers(HttpMethod.GET,"/products", "/products/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/static/images", "/static/images/**").permitAll()
     
                        .requestMatchers("/users", "/users/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                        .requestMatchers("/carts", "/carts/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                        .requestMatchers("/services", "/services/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                        .requestMatchers("/users", "/users/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST, "/feedbacks/**", "/feedback").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/feedbacks/**", "/feedback").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/feedbacks/**", "/feedback").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/feedbacks/**", "/feedback").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                        
                        
                        .requestMatchers("/categories", "/categories/**").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers("/cpanel", "/cpanel/**").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers("/emails", "/emails/**").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers("/orders", "/orders/**").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers("/payment", "/payment/**").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers("/whm", "/whm/**").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST,"/products", "/products/**").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/products", "/products/**").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/products", "/products/**").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PATCH,"/products", "/products/**").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST,"/products", "/products/**").hasAnyAuthority("ROLE_ADMIN")
                        
                        
//                        .requestMatchers("/payment", "/payment/**").hasAnyRole("ROLE_admin")
//                        .requestMatchers("/payment", "/payment/**").hasAnyRole("ROLE_admin")
                        

                        .anyRequest().authenticated();
//                        .anyRequest().permitAll();

                })
//                .httpBasic().and()
//                .formLogin()
//                .loginPage("http://127.0.0.1:5500/index.html")
//                .loginProcessingUrl("http://127.0.0.1:5500/razorpay.html")
//                .and()
                .oauth2Login()
                .authorizationEndpoint()
                    .baseUri("/oauth2/authorize")
                    .authorizationRequestRepository(httpCookieOAuth2AuthorizationRequestRepository)
                    .and()
                .redirectionEndpoint()
                    .baseUri("/oauth2/callback/*")
                    .and()
                .userInfoEndpoint()
                    .userService(customOAuth2UserService)
                    .and()
                .successHandler(oAuth2AuthenticationSuccessHandler)
                .failureHandler(oAuth2AuthenticationFailureHandler)
                .and()
                .addFilterBefore(jwtValidatorFilter, UsernamePasswordAuthenticationFilter.class);
//                .addFilterAfter(jwtTokenGeneratorFilter, OAuth2LoginAuthenticationFilter.class);
              
         	return http.build();
    }

 
    @Bean
    public AuthenticationManager authenticationManager() {
    	List<AuthenticationProvider> authenticationProviders = new ArrayList<>();
    	authenticationProviders.add(jwtAuthenticationProvider);
//    	authenticationProviders.add(oAuth2LoginAuthenticationProvider);
    	
    	return new ProviderManager(authenticationProviders);
	}
    

//    @Bean
//    public FilterRegistrationBean corsFilter() {
//    	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//    	
//    	CorsConfiguration corsConn = new CorsConfiguration();
//    	
//    	corsConn.setAllowCredentials(true);
//    	corsConn.addAllowedOriginPattern("*");
//    	corsConn.addAllowedOrigin("http://192.168.1.38:3000/");
//    	corsConn.addAllowedOrigin("http://127.0.0.1:5500");
////    	corsConn.addAllowedHeader("*");
//    	corsConn.addAllowedHeader("Authorization");
//    	corsConn.addAllowedHeader("Content-Type");
//    	corsConn.addAllowedHeader("Accept");
//    	corsConn.addAllowedMethod("POST");
//    	corsConn.addAllowedMethod("GET");
//    	corsConn.addAllowedMethod("DELETE");
//    	corsConn.addAllowedMethod("PUT");
////    	corsConn.addAllowedMethod("*");
//    	corsConn.setMaxAge(3600L);
//    	
//    	source.registerCorsConfiguration("/**", corsConn);
//    	
//    	FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
//    	
//    	return bean;
//    	
//    }
    
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//    	CorsConfiguration configuration = new CorsConfiguration();
//    	configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000","*"));
//    	configuration.setAllowedOrigins(Arrays.asList("*"));
//    	configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","PATCH","DELETE","OPTION"));
//    	configuration.setAllowedHeaders(Arrays.asList("*"));
////    	configuration.setAllowCredentials(true);
////    	configuration.setAllowedHeaders(Arrays.asList("Content-Type","Authorization","Access-Control-Allow-Credentials","Access-Control-Allow-Origin"));
//    	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////    	source.registerCorsConfiguration("/**", configuration);
//    	return source;
//    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
      CorsConfiguration configuration = new CorsConfiguration();
      configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000","http://localhost:5500","http://127.0.0.1:5500","https://dev.haarmk.com/"));
      configuration.setAllowedMethods(Arrays.asList("GET","POST","PATCH", "PUT", "DELETE", "OPTIONS", "HEAD"));
      configuration.setAllowCredentials(true);
      configuration.setAllowedHeaders(Arrays.asList("Authorization", "Requestor-Type","Content-type","*"));
      configuration.setExposedHeaders(Arrays.asList("X-Get-Header"));
      configuration.setMaxAge(3600L);
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", configuration);
      return source;
    }
    
    
    
}

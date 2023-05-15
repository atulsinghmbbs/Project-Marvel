package com.haarmk.filter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haarmk.exception.AuthenticationException;
import com.haarmk.exception.ErrorDetails;
import com.haarmk.service.interfaces.AuthService;
import com.haarmk.util.CookieUtils;
import com.haarmk.util.JwtUtil;
import com.haarmk.util.SecurityCipher;
import com.nimbusds.jose.Header;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtValidatorFilter extends OncePerRequestFilter{
	@Autowired private Environment environment;
	@Autowired private JwtUtil jwtUtil;
	@Autowired private HandlerExceptionResolver handlerExceptionResolver;
    @Value("${JWT_ACCESS_TOKEN_COOKIE_NAME}")
    String accessTokenCookieName;
    @Value("${JWT_REFRESH_TOKEN_COOKIE_NAME}")
    String refreshTokenCookieName;
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//		
//		String jwt = request.getHeader(environment.getProperty("JWT_HEADER"));
////		System.out.println("dsfhjsdhfksdjfksdfsdk"+jwt+);
//		if(jwt != null && !jwt.equals("null")) {
//						
//			try {
//
//				jwt = jwt.substring(7);
//		
//				
//				Claims claims = jwtUtil.validateToken(jwt); 				
//				
//				String username= (String)(claims.get("sub"));
//				String authorities= (String)claims.get("authorities");	
//				
//				Authentication auth = new UsernamePasswordAuthenticationToken(username, null, AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
//				System.out.println("auth name: "+auth.getName());
//				SecurityContextHolder.getContext().setAuthentication(auth);
//			} catch (AccessDeniedException | SignatureException | ExpiredJwtException e) {
//				request.setAttribute("err", e.getMessage());
//				handlerExceptionResolver.resolveException(request, response, null, e);
////				throw new AuthenticationException(e.getMessage());
//			}	
//					
//					
//				}
//				
//				filterChain.doFilter(request, response);
//				
//			}
	
//	String jwt = request.getHeader(environment.getProperty("JWT_HEADER"));
//	System.out.println("dsfhjsdhfksdjfksdfsdk"+jwt+);
	@Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJwtToken(httpServletRequest);
            if (StringUtils.hasText(jwt) && !jwt.equals("null")) {
//            	Boolean isTokenValid = jwtUtil.isTokenVAlid(jwt);
//            	if(!isTokenValid) throw new AuthenticationException("Token s not valid");
                Claims claims = jwtUtil.validateToken(jwt);
                
                String authorities= (String)claims.get("authorities");
                String username= (String)(claims.get("username"));
               
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                
                
                SecurityContextHolder.getContext().setAuthentication(authentication);
                
            }
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            
        } catch (AccessDeniedException | JwtException e) {
        		
			handlerExceptionResolver.resolveException(httpServletRequest, httpServletResponse, null, e);

        	
        }

        
    }

	 private String getJwtFromRequest(HttpServletRequest request) {
	        String bearerToken = request.getHeader("Authorization");
	        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
	            String accessToken = bearerToken.substring(7);
	            if (accessToken == null) return null;

//	            return SecurityCipher.decrypt(accessToken);
	            return accessToken;
	        }
	        return null;
	  }
	 
	 
	 private String getJwtFromCookie(HttpServletRequest request) {
	        Cookie[] cookies = request.getCookies();
	        if(cookies == null) return null;
	        for (Cookie cookie : cookies) {
	            if (accessTokenCookieName.equals(cookie.getName())) {
	                String accessToken = cookie.getValue();
	                if (accessToken == null) return null;
	                return SecurityCipher.decrypt(accessToken);
	            }
	        }
	        return null;
	    }
	 


	    private String getJwtToken(HttpServletRequest request) {
	        String cookiesJwt = getJwtFromCookie(request);
	        if(cookiesJwt != null) return cookiesJwt;
	        return getJwtFromRequest(request);
	    }

}

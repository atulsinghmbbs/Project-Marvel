/**
 * 
 */
package com.haarmk.filter;

import java.io.IOException;
import java.security.Principal;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author indicate0
 *
 */

@Component
public class JwtTokenGeneratorFilter extends OncePerRequestFilter{
//	@Autowired Environment environment;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		
		System.out.println(principal.getName());
		System.out.println(principal);
		
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		
		Boolean shouldNotFilter = request.getServletPath().equals("/auth/signin") || request.getServletPath().equals("/login/oauth2/code/google");
		
        return !shouldNotFilter;	
	}
}

/**
 * 
 */
package com.haarmk.service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.haarmk.dto.EmailDetails;
import com.haarmk.dto.LoginDto;
import com.haarmk.dto.LoginResDto;
import com.haarmk.dto.LoginResponse;
import com.haarmk.dto.RefreshTokenDto;
import com.haarmk.dto.SignupDto;
import com.haarmk.dto.TokenDto;
import com.haarmk.exception.EmailNotVerifiedException;
import com.haarmk.exception.TokenException;
import com.haarmk.model.AuthProvider;
import com.haarmk.model.Token;
import com.haarmk.model.User;
import com.haarmk.service.interfaces.AuthService;
import com.haarmk.service.interfaces.EmailService;
import com.haarmk.service.interfaces.TokenService;
import com.haarmk.service.interfaces.UserService;
import com.haarmk.util.CookieUtils;
import com.haarmk.util.JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Ankit Patel
 *
 */
@Service
public class AuthServiceImpl implements AuthService {
	
    
	@Autowired private UserService userService;
    @Autowired private EmailService emailService;
    @Autowired private JwtUtil jwtUtil;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private TokenService tokenService;
 	@Autowired private AuthenticationManager authenticationManager;
    @Autowired private Environment environment;
    @Autowired private CookieUtils cookieUtils;
    @Value("${JWT_ACCESS_TOKEN_TIME}")
    Long timeDelta;
    
    @Value("${JWT_VERIFICATION_TOKEN_TIME}")
    Long timeDeltaVerification;
    
    @Value("${JWT_REFRESH_TOKEN_TIME}")
    Long timeDeltaRefreshToken;
    
    @Value("${JWT_REFRESH_TOKEN_COOKIE_NAME}")
    private String refreshTokenCookieName;
    
    
    
    
    
    
//    ==========================================================Reset Password===============================================================
	
    
    
    
    
    public void requestPasswordReset(String email) {
		
		userService.getUserByEmail(email);
		Map<String, String> claims = new HashMap<>();
		claims.put("email_reset_password", email);
		String jwt = jwtUtil.generateToken(claims, timeDeltaVerification);
		Token token = tokenService.addToken(Token.builder().token(jwt).build());
		String[] recipients = {email};
		EmailDetails emailDetails = EmailDetails.builder()
				.recipients(recipients)
				.subject("HAARMK Rest password")
				.isHtml(Boolean.TRUE)
				.msgBody("<p>Please click the <a href="+environment.getProperty("frontend_baseurl")+"/reset-password?token="+token.getToken()+">link</a> to reset you password.</p>")
				.build();
				
		emailService.sendMail(emailDetails);
		
		
		
	}
    
    
    
    
    
	@Override
	public void resetPassword(String token, String newPassword) {
		Claims claims = jwtUtil.validateToken(token);
		try {
			tokenService.getToken(token);
			tokenService.deleteToken(token);
		} catch (TokenException e) {
			throw new TokenException("This token has been used.");
		}
		String email = claims.get("email_reset_password", String.class);
		if(email == null) {
			throw new TokenException("Not a valid token");
		}
		User foundUser = userService.getUserByEmail(email);
		foundUser.setPassword(passwordEncoder.encode(newPassword));
		userService.updateUser(foundUser);
		
	}
	
	
	
	
	
	
// =============================================verify email======================================================================
	
	
	
	
	
	
	@Override
	public void verifyEmailRequest(String email) {
		userService.getUserByEmail(email);
		Map<String, String> claims = new HashMap<>();
		claims.put("email_verification", email);
		String jwt = jwtUtil.generateToken(claims, timeDeltaVerification);
		Token token = tokenService.addToken(Token.builder().token(jwt).build());
		String[] recipients = {email};
		EmailDetails emailDetails = EmailDetails.builder()
				.recipients(recipients)
				.subject("HAARMK, verify email")
				.isHtml(Boolean.TRUE)
				.msgBody("<p>Please click the <a href="+environment.getProperty("frontend_baseurl")+"/verify-email?token="+token.getToken()+">link</a> to verify your email.</p>")
				.build();
				
		emailService.sendMail(emailDetails);
	}
	
	
	@Override
	public void verifyEmail(LoginResDto loginResDto) {
		String token = loginResDto.getToken();
		Claims claims = jwtUtil.validateToken(token);
		try {
			tokenService.getToken(token);
			tokenService.deleteToken(token);
		} catch (TokenException e) {
			throw new TokenException("This token has been used.");
		}
		String email = claims.get("email_verification", String.class);
		if(email == null) {
			throw new TokenException("Not a valid token");
		}
		User foundUser = userService.getUserByEmail(email);
		foundUser.setEnabled(true);
		userService.updateUser(foundUser);		
	}
	
	
	
	
//	===============================================================		login 	-  sign up    ===============================================================
	
	
	
	
	
	@Override
	public ResponseEntity<LoginResDto> login(LoginDto loginDto) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        User user =  (User) authentication.getPrincipal();
        if(!user.isEnabled()) {
        	verifyEmailRequest(user.getEmail());
        	throw new EmailNotVerifiedException("Please verify your email. A link has been sent to your registered email");
        }
        
        Map<String, String> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("authorities", authentication.getAuthorities().stream().map((a)->a.getAuthority().toUpperCase()).collect(Collectors.joining(",")));
        TokenDto tokenDtoAccessToken = jwtUtil.generateAccessToken(claims);
		LoginResDto loginResDto = new LoginResDto();
//		access token
		loginResDto.setToken(tokenDtoAccessToken.getTokenValue());
		loginResDto.setType("Authorization: Bearer");
//		refresh token
		TokenDto tokenDtoRefreshToken = jwtUtil.generateRefreshToken(claims);
		HttpHeaders responseHeaders = new HttpHeaders();
		addRefreshTokenCookie(responseHeaders, tokenDtoRefreshToken);
//        LoginResponse loginResponse = new LoginResponse(LoginResponse.SuccessFailure.SUCCESS, "Auth successful. Tokens are created in cookie.");
		return ResponseEntity.ok().headers(responseHeaders).body(loginResDto);
	}
	
	@Override
	public ResponseEntity<Void> logout(HttpServletRequest httppHttpServletRequest, HttpServletResponse httpServletResponse) {
		
		CookieUtils.deleteCookie(httppHttpServletRequest, httpServletResponse, refreshTokenCookieName);
		
		return ResponseEntity.ok().build();
	}
	
	@Override
	public User signup(SignupDto signupDto) {
//		Authority authority = authorityService.getAuthorityByAuthorityName("ROLE_user");
		
		
		
//		 user.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
//	        user.setProviderId(oAuth2UserInfo.getId());
//	        user.setFirstName(firstName);
//	        user.setLastName(lastName);
//	        user.setEmail(oAuth2UserInfo.getEmail());
//	        user.setImage(oAuth2UserInfo.getImageUrl());
		
		
//    	Long next_id = userService.getAutoIncrementValue();
    	User user = User.builder()
    			.firstName(signupDto.getFirstName())
    			.lastName(signupDto.getLastName())
    			.email(signupDto.getEmail())
//    			.username("HIPL"+(next_id+1))
    			.password(signupDto.getPassword())
    			.enabled(false)
    			.build();
    	
    	user.setProvider(AuthProvider.haarmk);
//    	user.setProviderId(AuthProvider.haarmk.toString());
    	
//    	user.getAuthorities().add(authority);
//    	user.getCart().setUser(user);
//    	if(user.getPassword()!=null) {
//    		user.setPassword(passwordEncoder.encode(user.getPassword()));
//    	}
    	User addedUser = userService.registerUser(user);
    	if(!user.isEnabled())
    		verifyEmailRequest(user.getEmail());
		return addedUser;
	}
	
	
	
	
//	@Override
//	public User registerUser(User user) {
//		Authority authority = authorityService.getAuthorityByAuthorityName("ROLE_user");
//		Long next_id = userService.getAutoIncrementValue();
//		user.setUsername("HIPL"+(next_id+1));
//		user.getAuthorities().add(authority);
//		user.getCart().setUser(user);
//		if(user.getPassword()!=null) {
//			user.setPassword(passwordEncoder.encode(user.getPassword()));
//		}
//		User addedUser = userService.addUser(user);
//		verifyEmailRequest(user.getEmail());
//		return addedUser;
//	}
	
	
//	================================================refresh token====================================================================
	
	
	
	@Override
	public LoginResDto refreshToken(RefreshTokenDto refreshTokenDto) {
		Claims claims = jwtUtil.validateToken(refreshTokenDto.getToken());
        Map<String, String> claimsMap = new HashMap<>();
        claimsMap.put("username", claims.get("username").toString());
        claimsMap.put("authorities", claims.get("authorities").toString());
        TokenDto tokenDto = jwtUtil.generateAccessToken(claimsMap);
		LoginResDto loginResDto = new LoginResDto();
		loginResDto.setToken(tokenDto.getTokenValue());
		loginResDto.setType("Authorization: Bearer");
		return loginResDto;
	}
	
	
	@Override
	public ResponseEntity<LoginResponse> getAccessToken(LoginResDto loginResDto) {
		Claims claims = jwtUtil.validateToken(loginResDto.getToken());
		Map<String, String> claimsMap = new HashMap<>();
        claimsMap.put("username", claims.get("username").toString());
        claimsMap.put("authorities", claims.get("authorities").toString());
		TokenDto tokenDtoRefreshToken = jwtUtil.generateRefreshToken(claimsMap);
		HttpHeaders responseHeaders = new HttpHeaders();
		addRefreshTokenCookie(responseHeaders, tokenDtoRefreshToken);
		return refresh(tokenDtoRefreshToken.getTokenValue());
	}





	@Override
	public ResponseEntity<LoginResponse> refresh(String decryptedRefreshToken) {
		Boolean refreshTokenValid = jwtUtil.isTokenVAlid(decryptedRefreshToken);
        if (!refreshTokenValid) {
            throw new JwtException("Refresh Token is invalid!");
        }

        Claims claims = jwtUtil.validateToken(decryptedRefreshToken);
        Map<String, String> claimsMap = new HashMap<>();
        claimsMap.put("username", claims.get("username").toString());
        claimsMap.put("authorities", claims.get("authorities").toString());
        TokenDto newAccessToken = jwtUtil.generateAccessToken(claimsMap);
		LoginResDto loginResDto = new LoginResDto();
//		access token
		loginResDto.setToken(newAccessToken.getTokenValue());
		loginResDto.setType("Authorization: Bearer");
		
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(HttpHeaders.SET_COOKIE, cookieUtils.createAccessTokenCookie(newAccessToken.getTokenValue(), newAccessToken.getDuration()).toString());
        LoginResponse loginResponse = new LoginResponse(LoginResponse.SuccessFailure.SUCCESS, "Auth successful. Tokens are created in cookie.");
        return ResponseEntity.ok().headers(responseHeaders).body(loginResponse);
	}

	
	
	
//	@Override
//	public ResponseEntity<LoginResponse> loginRef(@Valid LoginDto loginDto, String decryptedAccessToken,
//			String decryptedRefreshToken) {
//		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
//        User user =  (User) authentication.getPrincipal();
//        
////        if(!user.isEnabled()) {
////        	verifyEmailRequest(user.getEmail());
////        	throw new EmailNotVerifiedException("Please verify your email. A link has been sent to your registered email");
////        }
//        
//        Boolean accessTokenValid = jwtUtil.isTokenVAlid(decryptedAccessToken);
//        Boolean refreshTokenValid = jwtUtil.isTokenVAlid(decryptedRefreshToken);
//        Map<String, String> claimsMap = new HashMap<>();
//        claimsMap.put("username", user.getUsername());
//        claimsMap.put("authorities", jwtUtil.populateAuthorities(user.getAuthorities()));
//        
//        
//        HttpHeaders responseHeaders = new HttpHeaders();
//        TokenDto newAccessToken;
//        TokenDto newRefreshToken;
//        if (!accessTokenValid && !refreshTokenValid) {
//            newAccessToken = jwtUtil.generateAccessToken(claimsMap);
//            newAccessToken = jwtUtil.generateAccessToken(claimsMap);
//            newRefreshToken = jwtUtil.generateRefreshToken(claimsMap);
//            addAccessTokenCookie(responseHeaders, newAccessToken);
//            addRefreshTokenCookie(responseHeaders, newRefreshToken);
//        }
//
//        if (!accessTokenValid && refreshTokenValid) {
//            newAccessToken = jwtUtil.generateAccessToken(claimsMap);
//            addAccessTokenCookie(responseHeaders, newAccessToken);
//        }
//
//        if (accessTokenValid && refreshTokenValid) {
//            newAccessToken = jwtUtil.generateAccessToken(claimsMap);
//            newRefreshToken = jwtUtil.generateRefreshToken(claimsMap);
//            addAccessTokenCookie(responseHeaders, newAccessToken);
//            addRefreshTokenCookie(responseHeaders, newRefreshToken);
//        }
//
//        LoginResponse loginResponse = new LoginResponse(LoginResponse.SuccessFailure.SUCCESS, "Auth successful. Tokens are created in cookie.");
//        return ResponseEntity.ok().headers(responseHeaders).body(loginResponse);
//	}	
	
	private void addAccessTokenCookie(HttpHeaders httpHeaders, TokenDto token) {
        httpHeaders.add(HttpHeaders.SET_COOKIE, cookieUtils.createAccessTokenCookie(token.getTokenValue(), token.getDuration()).toString());
    }

    private void addRefreshTokenCookie(HttpHeaders httpHeaders, TokenDto token) {
        httpHeaders.add(HttpHeaders.SET_COOKIE, cookieUtils.createRefreshTokenCookie(token.getTokenValue(), token.getDuration()).toString());
    }
	
}

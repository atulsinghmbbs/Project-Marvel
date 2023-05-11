package com.haarmk.security.oauth2;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.haarmk.exception.OAuth2AuthenticationProcessingException;
import com.haarmk.model.AuthProvider;
import com.haarmk.model.User;
import com.haarmk.repository.UserRepo;
import com.haarmk.security.UserPrincipal;
import com.haarmk.security.oauth2.user.OAuth2UserInfo;
import com.haarmk.security.oauth2.user.OAuth2UserInfoFactory;
import com.haarmk.service.interfaces.AuthService;
import com.haarmk.service.interfaces.UserService;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired private UserService userService;
//    @Autowired private AuthService authService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        
        if(StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }
        
        User userOptional;
        try {
        	userOptional = userService.getUserByEmail(oAuth2UserInfo.getEmail());
		} catch (UsernameNotFoundException e) {
			userOptional = null;
		}
         
        User user;
        if(userOptional != null) {
            user = userOptional;
            if(!user.getProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
                throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
                        user.getProvider() + " account. Please use your " + user.getProvider() +
                        " account to login.");
            }
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        }

        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }

    private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
    	
    	String[] names = oAuth2UserInfo.getName().split(" ");
    	String firstName = "";
    	String lastName = "";
    	for(int i = 0; i < names.length; i++) {
    		if(i == 0) {
    			firstName += names[i];
    		}else {
    			lastName+=names[i];
    		}
    	}
    	
        User user = new User();
        user.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
        user.setProviderId(oAuth2UserInfo.getId());
        user.setFirstName(firstName);
        user.setEnabled(oAuth2UserInfo.emailVerified());
        user.setLastName(lastName);
        user.setEmail(oAuth2UserInfo.getEmail());
        user.setImage(oAuth2UserInfo.getImageUrl());
        return userService.registerUser(user);
    }

    private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
    	String[] names = oAuth2UserInfo.getName().split(" ");
    	String firstName = "";
    	String lastName = "";
    	for(int i = 0; i < names.length; i++) {
    		if(i == 0) {
    			firstName += names[i];
    		}else {
    			lastName+=names[i];
    		}
    	}
    			
        existingUser.setFirstName(firstName);
        existingUser.setLastName(lastName);
        existingUser.setEnabled(oAuth2UserInfo.emailVerified());
        existingUser.setImage(oAuth2UserInfo.getImageUrl());
        return userService.updateUser(existingUser);
    }

}

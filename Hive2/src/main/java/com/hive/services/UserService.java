package com.hive.services;

import com.hive.config.AppConfig;
import com.hive.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private AppConfig appConfig;

    public boolean registerUser(User user) {
        try {
            // Hash password before sending to API
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            
            		String apiUrl = appConfig.getUserApiUrl() + "/register";
            Boolean result = restTemplate.postForObject(apiUrl, user, Boolean.class);
            return result != null && result;
        } catch (Exception e) {
            // Log the error in production
            return false;
        }
    }

    public User authenticateUser(String email, String rawPassword) {
        try {
            String apiUrl = appConfig.getUserApiUrl() + "/getUser/" + email;
            User user = restTemplate.getForObject(apiUrl, User.class);
            
            if (user != null && passwordEncoder.matches(rawPassword, user.getPassword())) {
                return user;
            }
            return null;
        } catch (Exception e) {
            // Log the error in production
            return null;
        }
    }

    public User updateUser(User user) {
        try {
            String apiUrl = appConfig.getUserApiUrl() + "/updateUser";
            return restTemplate.postForObject(apiUrl, user, User.class);
        } catch (Exception e) {
            // Log the error in production
            return null;
        }
    }

    public boolean updatePassword(String userEmail, String oldPassword, String newPassword) {
        try {
            // Verify old password first
            User user = getUserByEmail(userEmail);
            if (user == null || !passwordEncoder.matches(oldPassword, user.getPassword())) {
                return false;
            }

            Map<String, String> passwordData = new HashMap<>();
            passwordData.put("userEmail", userEmail);
            passwordData.put("newPassword", passwordEncoder.encode(newPassword));

            String apiUrl = appConfig.getUserApiUrl() + "/updatePassword";
            Boolean result = restTemplate.postForObject(apiUrl, passwordData, Boolean.class);
            return result != null && result;
        } catch (Exception e) {
            // Log the error in production
            return false;
        }
    }

    public User getUserByEmail(String email) {
        try {
            String apiUrl = appConfig.getUserApiUrl() + "/getUser/" + email;
            return restTemplate.getForObject(apiUrl, User.class);
        } catch (Exception e) {
            // Log the error in production
            return null;
        }
    }

    public boolean updateUserPhoto(String email, byte[] photo) {
        try {
            String apiUrl = appConfig.getUserApiUrl() + "/updatePhoto/" + email;
            restTemplate.put(apiUrl, photo);
            return true;
        } catch (Exception e) {
            // Log the error in production
            return false;
        }
    }

    public byte[] getUserPhoto(String email) {
        try {
            String apiUrl = appConfig.getUserApiUrl() + "/getPhoto/" + email;
            return restTemplate.getForObject(apiUrl, byte[].class);
        } catch (Exception e) {
            // Log the error in production
            return null;
        }
    }
} 
package com.hive.controllers;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.hive.models.User;
import com.hive.services.UserService;

import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/AfterLogin")
	public String AfterLogin() {
		return "AfterLogin";
	}
	
	
	@GetMapping("/dash")
	public String dash() {
		return "dash";
	}
	
	@GetMapping("/profileEdit")
	public String profileEdit(HttpSession session) {
		if(session==null) {
			return "LoginPage";
		}else {
			return "profileEdit";
		}
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute User user, HttpSession session, ModelMap m) {
		try {
			if (userService.registerUser(user)) {
				session.setAttribute("user", user);
				return "redirect:/user/AfterLogin";
			} else {
				m.addAttribute("msg", "Email ID Already Exists!");
				return "Signup";
			}
		} catch (Exception e) {
			m.addAttribute("msg", "Registration failed. Please try again.");
			return "Signup";
		}
	}
	
	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password, 
	                   HttpSession session, ModelMap m) {
		try {
			User user = userService.authenticateUser(email, password);
			if (user != null) {
				session.setAttribute("user", user);
				return "redirect:/user/AfterLogin";
			} else {
				session.setAttribute("message", "Invalid Credentials!");
				return "redirect:/LoginPage";
			}
		} catch (Exception e) {
			session.setAttribute("message", "Login failed. Please try again.");
			return "redirect:/LoginPage";
		}
	}
	
	@PostMapping("/updateUser")
	public String updateUser(HttpSession session, @Valid @ModelAttribute User user, ModelMap m) {
		try {
			User updatedUser = userService.updateUser(user);
			if (updatedUser != null) {
				session.setAttribute("user", updatedUser);
				m.addAttribute("msg", "Update Success!");
			} else {
				m.addAttribute("msg", "Update Failed!");
			}
		} catch (Exception e) {
			m.addAttribute("msg", "Update failed. Please try again.");
		}
		return "redirect:/user/profileEdit";
	}
	
	@PostMapping("/updatePhoto")
	public String updatePhoto(HttpSession session, @RequestPart MultipartFile photo, ModelMap m) {
		try {
			User user = (User) session.getAttribute("user");
			if (user != null && userService.updateUserPhoto(user.getEmail(), photo.getBytes())) {
				// Refresh user data
				User updatedUser = userService.getUserByEmail(user.getEmail());
				if (updatedUser != null) {
					session.setAttribute("user", updatedUser);
				}
				m.addAttribute("msg", "Photo updated successfully!");
			} else {
				m.addAttribute("msg", "Photo update failed!");
			}
		} catch (Exception e) {
			m.addAttribute("msg", "Photo update failed. Please try again.");
		}
		return "redirect:/user/profileEdit";
	}
	
	@GetMapping("/getPhoto")
	public void getPhoto(HttpSession session, ServletResponse response) throws IOException {
		try {
			User user = (User) session.getAttribute("user");
			if (user != null) {
				byte[] image = userService.getUserPhoto(user.getEmail());
				if (image == null || image.length == 0) {
					InputStream is = this.getClass().getClassLoader().getResourceAsStream("static/images/person.png");
					image = is.readAllBytes();
				}
				response.getOutputStream().write(image);
			}
		} catch (Exception e) {
			// Return default image on error
			InputStream is = this.getClass().getClassLoader().getResourceAsStream("static/images/person.png");
			byte[] image = is.readAllBytes();
			response.getOutputStream().write(image);
		}
	}
	
	@PostMapping("/updatePassword")
	public String updatePassword(@RequestParam String userEmail, @RequestParam String userPassword, 
	                           @RequestParam String newPassword, HttpSession session, ModelMap m) {
		try {
			if (userService.updatePassword(userEmail, userPassword, newPassword)) {
				m.addAttribute("msg", "Password Update Success!");
			} else {
				m.addAttribute("msg", "Invalid Old Password!");
			}
		} catch (Exception e) {
			m.addAttribute("msg", "Password update failed. Please try again.");
		}
		return "profileEdit";
	}
}

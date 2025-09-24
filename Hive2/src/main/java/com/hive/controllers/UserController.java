package com.hive.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.hive.models.Address;
import com.hive.models.Booking;
import com.hive.models.User;
import com.hive.services.BookingService;

import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	BookingService bookingService; 
	
	private RestTemplate restTemplate=new RestTemplate();
	private String URL="http://localhost:2003/user";
	
	@GetMapping("/AfterLogin")
	public String AfterLogin() {
		return "AfterLogin";
	}
	
	
	@GetMapping("/dash")
	public String dash() {
		return "dash";
	}
	
	@GetMapping("/userBooking")
	public String userBooking(HttpSession session,ModelMap m) {
		User user=(User) session.getAttribute("user");
		if(user==null) {
			return "LoginPage";
		}else {
			List<Booking> bookings=bookingService.getBookings(user.getEmail());
			m.addAttribute("bookings", bookings);
			return "userBookings";
		}
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
	public String register(@ModelAttribute User user,HttpSession session, ModelMap m) {
		//user.setUserPassword(user.getUserPassword());
		String API="/register";
		HttpEntity<User> requestEntity=new HttpEntity<User>(user);
		ResponseEntity<Boolean> result= restTemplate.exchange(URL+API,HttpMethod.POST,requestEntity,Boolean.class);
		if(result.getBody()) {
			session.setAttribute("user", user);
			return "redirect:/user/AfterLogin";
		}else {
			m.addAttribute("msg","Email ID Already Exist!");
			return "Signup";
		}
	}
	
	@PostMapping("/login")
	public String login(@RequestParam String email,@RequestParam String password, HttpSession session,  ModelMap m) {
		String API="/getUser/"+email;
		ResponseEntity<User> result= restTemplate.exchange(URL+API,HttpMethod.GET, null, User.class);
		User user=result.getBody();
		if(user!=null && password.equalsIgnoreCase(user.getPassword())) {
			session.setAttribute("user", user);
			return "redirect:/user/AfterLogin";
		}else {
			//m.addAttribute("msg","Invalid Credentials!");
			session.setAttribute("message", "Invalid Credentials!");
			return "redirect:/LoginPage";
		}
	}
	
	@PostMapping("/updateUser")
	public String updateUser(HttpSession session, @ModelAttribute User user, @ModelAttribute Address address, ModelMap m) {
		String API="/updateUser";
		user.setAddress(address);
		HttpEntity<User> requestEntity=new HttpEntity<User>(user);
		ResponseEntity<User> result=restTemplate.exchange(URL+API,HttpMethod.PUT,requestEntity,User.class);
		if(result.getBody()!=null) {
			session.setAttribute("user", result.getBody());
			m.addAttribute("msg","Updation Success!");
		}else {
			m.addAttribute("msg","Updation Failed!");
		}
		return "redirect:/user/profileEdit";
	}
	
	@PostMapping("/updatePhoto")
	public String updatePhoto(HttpSession session,@RequestPart MultipartFile photo,ModelMap m) throws IOException {
		User user=(User)session.getAttribute("user");
		String API="/updatePhoto/"+user.getEmail();
		HttpEntity<byte[]> requestEntity=new HttpEntity<>(photo.getBytes());
		restTemplate.put(URL+API, requestEntity);
		m.addAttribute("msg","Photo updated successfully!");
		API="/getUser/"+user.getEmail();
		ResponseEntity<User> result=restTemplate.exchange(URL+API,HttpMethod.GET,null,User.class);
		user=result.getBody();
		session.setAttribute("user", user);
		return "redirect:/user/profileEdit";
	}
	
	@GetMapping("/getPhoto")
	public void getPhoto(HttpSession session,ServletResponse response) throws IOException {
		User user=(User)session.getAttribute("user");
		String API="/getPhoto/"+user.getEmail();
		ResponseEntity<byte[]> result=restTemplate.exchange(URL+API, HttpMethod.GET, null, byte[].class);
		byte image[]=result.getBody();
		if(image==null || image.length==0 ) {
			InputStream is = this.getClass().getClassLoader().getResourceAsStream("static/images/person.png");
			image=is.readAllBytes();
		}
		response.getOutputStream().write(image);
	}
	
	@PostMapping("/updatePassword")
	public String updatePassword(@RequestParam String userEmail,@RequestParam String userPassword,@RequestParam String newPassword, HttpSession session, ModelMap m) {
		String API="/getUser/"+userEmail;
		ResponseEntity<User> result= restTemplate.exchange(URL+API,HttpMethod.GET, null, User.class);
		User user=result.getBody();
		if(user!=null && user.getPassword().equalsIgnoreCase(userPassword)) {
			
			Map<String, String> data=new HashMap<>();
			data.put("userEmail", userEmail);
			data.put("newPassword", newPassword);
			
			HttpEntity<Map<String, String>> requestEntity=new HttpEntity<Map<String, String>>(data);
			API="/updatePassword";
			ResponseEntity<Boolean> r= restTemplate.exchange(URL+API,HttpMethod.PUT, requestEntity, Boolean.class);
			if(r.getBody()) {
				m.addAttribute("msg","Password Updation Success!");
			}else {
				session.invalidate();
				return "redirect:/login-signup";
			}
		}else {
			m.addAttribute("msg","Invalid OLD Password!");
		}
		return "profileEdit";
	}
}

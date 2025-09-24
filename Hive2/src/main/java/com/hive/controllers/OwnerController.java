package com.hive.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.io.InputStream;

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
import org.springframework.web.client.RestTemplate;

import com.hive.models.Owner;

import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/owner")
public class OwnerController {

	private RestTemplate restTemplate=new RestTemplate();
	private String URL="http://localhost:2003/owner";
	
	@GetMapping("/Owner")
	public String Owner(HttpSession session) {
		if(session.getAttribute("owner")!=null) {
			return "Owner";
		}
		return "OwnerLogin";
	}
	
	@GetMapping("/addProperty")
	public String addProperty() {
		return "addProperty";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute Owner owner,HttpSession session, ModelMap m) {
		String API="/register";
		HttpEntity<Owner> requestEntity=new HttpEntity<Owner>(owner);
		ResponseEntity<Owner> result= restTemplate.exchange(URL+API,HttpMethod.POST,requestEntity,Owner.class);
		Owner o=result.getBody();
		if(o!=null) {
			session.setAttribute("owner", o);
			return "redirect:/owner/Owner";
		}else {
			m.addAttribute("msg","Email ID Already Exist!");
			return "Signup";
		}
	}
	
	@PostMapping("/login")
	public String login(@RequestParam String email,@RequestParam String password, HttpSession session,  ModelMap m) throws JsonProcessingException {
		String API="/getOwner/"+email;
		ResponseEntity<Owner> result= restTemplate.exchange(URL+API,HttpMethod.GET, null,Owner.class);
		Owner owner=result.getBody();
		if(owner!=null && password.equals(owner.getPassword())) {
			session.setAttribute("owner", owner);
			return "redirect:/owner/Owner";
		}else {
			m.addAttribute("msg","Invalid Credentials!");
			return "OwnerLogin";
		}
	}
//	
//	@PostMapping("/updateOwner")
//	public String updateOwner(HttpSession session, @ModelAttribute Owner owner, ModelMap m) {
//		String API = "/updateOwner";
//		HttpEntity<Owner> requestEntity = new HttpEntity<Owner>(owner);
//		ResponseEntity<Owner> result = restTemplate.exchange(URL + API, HttpMethod.PUT, requestEntity, Owner.class);
//		if (result.getBody() != null) {
//			session.setAttribute("owner", result.getBody());
//			m.addAttribute("msg", "Updation Success!");
//		} else {
//			m.addAttribute("msg", "Updation Failed!");
//		}
//		return "redirect:/owner/Owner";
//	}
//
//	@PostMapping("/updatePhoto")
//	public String updatePhoto(HttpSession session, @RequestPart MultipartFile ownerImg, ModelMap m) throws IOException {
//		Owner owner = (Owner) session.getAttribute("owner");
//		String API = "/updatePhoto/" + owner.getOwnerEmail();
//		HttpEntity<byte[]> requestEntity = new HttpEntity<>(ownerImg.getBytes());
//		restTemplate.put(URL + API, requestEntity);
//		m.addAttribute("msg", "Photo updated successfully!");
//		API = "/getOwner/" + owner.getOwnerEmail();
//		ResponseEntity<Owner> result = restTemplate.exchange(URL + API, HttpMethod.GET, null, Owner.class);
//		owner = result.getBody();
//		session.setAttribute("owner", owner);
//		return "redirect:/owner/Owner";
//	}
//
	@GetMapping("/getPhoto")
	public void getPhoto(HttpSession session, ServletResponse response) throws IOException {
		Owner owner = (Owner) session.getAttribute("owner");
		String API = "/getPhoto/" + owner.getEmail();
		ResponseEntity<byte[]> result = restTemplate.exchange(URL + API, HttpMethod.GET, null, byte[].class);
		byte[] image = result.getBody();
		if (image == null || image.length == 0) {
			InputStream is = this.getClass().getClassLoader().getResourceAsStream("static/images/person.png");
			image = is.readAllBytes();
		}
		response.getOutputStream().write(image);
	}
//
//	@PostMapping("/updatePassword")
//	public String updatePassword(@RequestParam String ownerEmail, @RequestParam String ownerPassword,
//			@RequestParam String newPassword, HttpSession session, ModelMap m) {
//		String API = "/getOwner/" + ownerEmail;
//		ResponseEntity<Owner> result = restTemplate.exchange(URL + API, HttpMethod.GET, null, Owner.class);
//		Owner owner = result.getBody();
//		if (owner != null && owner.getOwnerPassword().equalsIgnoreCase(ownerPassword)) {
//
//			Map<String, String> data = new HashMap<>();
//			data.put("ownerEmail", ownerEmail);
//			data.put("newPassword", newPassword);
//
//			HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(data);
//			API = "/updatePassword";
//			ResponseEntity<Boolean> r = restTemplate.exchange(URL + API, HttpMethod.PUT, requestEntity, Boolean.class);
//			if (r.getBody()) {
//				m.addAttribute("msg", "Password Updation Success!");
//			} else {
//				session.invalidate();
//				return "redirect:/OwnerLogin";
//			}
//		} else {
//			m.addAttribute("msg", "Invalid OLD Password!");
//		}
//		return "Owner";
//	}
//
//	
}

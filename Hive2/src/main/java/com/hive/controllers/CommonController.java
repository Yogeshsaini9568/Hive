package com.hive.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommonController {

	@RequestMapping("/")
	public String home(HttpSession session) {
		session.invalidate();
		return "index";
	}
	
	@RequestMapping("/Hostels")
	public String Hosttels() {
		return "Hostels";
	}
	
	@RequestMapping("/House")
	public String House() {
		return "House";
	}
	
	@RequestMapping("/Flat")
	public String Flat() {
		return "Flat";
	}
	
	
	@RequestMapping("/PGs")
	public String PGs() {
		return "PGs";
	}
	
	@RequestMapping("/LoginPage")
	public String loginPage() {
	    return "LoginPage";
	}
	
	@RequestMapping("/OwnerLogin")
	public String OwnerLogin() {
	    return "OwnerLogin";
	}
	
	@RequestMapping("/images")
	public String images() {
	    return "images";
	}
	
	@RequestMapping("/signup")
	public String Signup() {
		return "Signup";
	}
	
	@RequestMapping("/OwnerSignup")
	public String OwnerSignup() {
		return "OwnerSignup";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	
	
}

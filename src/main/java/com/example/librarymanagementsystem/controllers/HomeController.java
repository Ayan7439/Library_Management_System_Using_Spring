package com.example.librarymanagementsystem.controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String redirectToHome() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	
		if (principal instanceof UserDetails userDetails) {
			Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
	
			if (roles.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
				return "redirect:/admin";
			} else if (roles.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_EMPLOYEE"))) {
				return "redirect:/employee";
			} else if (roles.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_USER"))) {
				return "redirect:/users";
			}
		}
	
		// fallback to login if unauthenticated or no role matched
		return "redirect:/login";
	}
}
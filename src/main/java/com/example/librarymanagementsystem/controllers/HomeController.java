package com.example.librarymanagementsystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showStyledHomePage() {
        return "home"; // Renders home.html
    }

    // Optional: role redirect logic after login success
    @GetMapping("/role-redirect")
    public String redirectToDashboard() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof UserDetails userDetails) {
            var roles = userDetails.getAuthorities();
            if (roles.stream().anyMatch(authz -> authz.getAuthority().equals("ROLE_ADMIN"))) {
                return "redirect:/admin";
            } else if (roles.stream().anyMatch(authz -> authz.getAuthority().equals("ROLE_EMPLOYEE"))) {
                return "redirect:/employee";
            } else if (roles.stream().anyMatch(authz -> authz.getAuthority().equals("ROLE_USER"))) {
                return "redirect:/users";
            }
        }
        return "redirect:/login";
    }
}

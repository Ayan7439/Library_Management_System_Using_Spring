package com.example.librarymanagementsystem.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginRedirectController {

    @GetMapping("/redirectByRole")
    public String redirectByRole(Authentication authentication) {
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            String role = authority.getAuthority();
            if (role.equals("ROLE_ADMIN")) {
                return "redirect:/admin"; // change to your admin dashboard URL
            } else if (role.equals("ROLE_EMPLOYEE")) {
                return "redirect:/employee"; // change to your employee dashboard URL
            } else if (role.equals("ROLE_USER")) {
                return "redirect:/users"; // change to your user homepage or dashboard
            }
        }

        // If no known role is found, send back to login page with error
        return "redirect:/login?error";
    }
}

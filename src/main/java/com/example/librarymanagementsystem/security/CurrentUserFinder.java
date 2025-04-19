package com.example.librarymanagementsystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.librarymanagementsystem.entities.User;
import com.example.librarymanagementsystem.services.UserService;

@Service
public class CurrentUserFinder {

	@Autowired
	UserService usService;
	
	public long getCurrentUserId() {
		UserDetails details = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = details.getUsername();
		long userId = -1;
		for (User user : usService.findAll()) {
			if (user.getUsername().equals(username)) {
				userId = user.getUserId();
				break;		
			}
		}
		return userId;
	}
	
	public User getCurrentUser() {
		User currentUser = usService.findById(getCurrentUserId());
		return currentUser;
	}
}

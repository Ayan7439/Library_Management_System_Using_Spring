package com.example.librarymanagementsystem.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class User {

    @Id
    private int userId;

    @NotNull
    @Size(min=2, max=12)

    private String Username;

    @NotNull
    @Size(min=2, max=12)

    private String PasswordHash;

    private String Role;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPasswordHash() {
        return PasswordHash;
    }

    public void setPasswordHash(String Password) {
        this.PasswordHash = Password;
    }

    public String getRole(){
        return Role;
    }
    
    public void setRole(String Role){
        this.Role=Role;
    }

    public String toString() {
		return "User(UserId: " + this.userId + ", UserName: " + this.Username + ", PasswordHash: " + this.PasswordHash + ", Role: " + this.Role +")";
	}
    
}
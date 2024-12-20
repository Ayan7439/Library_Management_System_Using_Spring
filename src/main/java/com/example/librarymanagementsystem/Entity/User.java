package com.example.librarymanagementsystem.Entity;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    private int UserId;
    private String Username;
    private String PasswordHash;
    private String Role;
    //private String UserName;
    //private String Password;

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
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
    // public String getLastName() {
    //     return LastName;
    // }

    // public void setLastName(String LastName) {
    //     this.LastName = LastName;
    // }

    // public int getContactNo() {
    //     return ContactNo;
    // }

    // public void setContactNo(int ContactNo) {
    //     this.ContactNo = ContactNo;
    // }

    // public String getUserName() {
    //     return UserName;
    // }

    // public void setUserName(String UserName) {
    //     this.UserName = UserName;
    // }

    
}
package com.example.librarymanagementsystem.Entity;

import jakarta.persistence.*;

@Entity
public class Admin {

    @Id
    private int AdminId;
    private String FirstName;
    private String LastName;
    public int ContactNo;
    private String UserName;
    private String Password;

    public int getAdminId() {
        return AdminId;
    }

    public void setAdminId(int AdminId) {
        this.AdminId = AdminId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public int getContactNo() {
        return ContactNo;
    }

    public void setContactNo(int ContactNo) {
        this.ContactNo = ContactNo;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

}
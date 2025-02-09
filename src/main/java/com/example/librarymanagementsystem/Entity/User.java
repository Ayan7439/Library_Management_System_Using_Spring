// package com.example.librarymanagementsystem.Entity;

// import jakarta.persistence.*;
// import jakarta.validation.constraints.Min;
// import jakarta.validation.constraints.NotNull;
// import jakarta.validation.constraints.Size;

// @Entity
// public class User {

//     @Id
//     private int userId;

//     @NotNull
//     @Size(min=2, max=12)

//     private String Username;

//     @NotNull
//     @Size(min=2, max=12)

//     private String PasswordHash;

//     private String Role;

//     public int getUserId() {
//         return userId;
//     }

//     public void setUserId(int userId) {
//         this.userId = userId;
//     }

//     public String getUsername() {
//         return Username;
//     }

//     public void setUsername(String Username) {
//         this.Username = Username;
//     }

//     public String getPasswordHash() {
//         return PasswordHash;
//     }

//     public void setPasswordHash(String Password) {
//         this.PasswordHash = Password;
//     }

//     public String getRole(){
//         return Role;
//     }
    
//     public void setRole(String Role){
//         this.Role=Role;
//     }

//     public String toString() {
// 		return "User(UserId: " + this.userId + ", UserName: " + this.Username + ", PasswordHash: " + this.PasswordHash + ", Role: " + this.Role +")";
// 	}
    
// }

package com.example.librarymanagementsystem.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String username;
    private String passwordHash;
    private String role;

    public User() {}

    public User(Integer userId, String username, String passwordHash, String role) {
        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}

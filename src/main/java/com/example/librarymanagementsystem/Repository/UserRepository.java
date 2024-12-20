
package com.example.librarymanagementsystem.Repository;


import org.springframework.data.repository.CrudRepository;

import com.example.librarymanagementsystem.Entity.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called AdminRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {

}
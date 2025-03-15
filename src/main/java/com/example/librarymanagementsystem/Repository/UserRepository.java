package com.example.librarymanagementsystem.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.librarymanagementsystem.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
		
}

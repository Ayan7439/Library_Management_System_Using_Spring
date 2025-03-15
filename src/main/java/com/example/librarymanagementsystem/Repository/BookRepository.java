package com.example.librarymanagementsystem.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.librarymanagementsystem.entities.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

}
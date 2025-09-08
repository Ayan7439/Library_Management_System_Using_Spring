package com.example.librarymanagementsystem.controllers;

import com.example.librarymanagementsystem.entities.Book;
import com.example.librarymanagementsystem.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public String showBooks(Model model) {
        List<Book> allBooks = bookService.findAll();  
        model.addAttribute("books", allBooks);
        return "books"; // Will return to books.html
    }
}

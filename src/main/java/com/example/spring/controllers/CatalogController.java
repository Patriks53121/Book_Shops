package com.example.spring.controllers;

import com.example.spring.models.Book;
import com.example.spring.repositories.BookRepository;
import com.example.spring.services.classes.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CatalogController {

    @Autowired
    private BookRepository bookRepo;

    @GetMapping("/catalog")
    public String getCatalog(Model model) {
        List<Book> books = bookRepo.findAll();
        model.addAttribute("books", books);
        return "catalog";
    }


}

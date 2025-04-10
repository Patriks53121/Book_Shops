package com.example.spring.controllers;

import com.example.spring.models.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CatalogController {

    @GetMapping("/catalog")
    public String getCatalog(Model model) {

        List<Book> books = AddController.books;

        model.addAttribute("books", books);

        return "catalog";
    }


}

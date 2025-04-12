package com.example.spring.controllers;

import com.example.spring.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import com.example.spring.models.Book;

@Controller
public class DeleteController {

    @Autowired
    private BookRepository bookRepo;

    @GetMapping("/delete")
    public String getDelete(Model model) {
        model.addAttribute("books", bookRepo.findAll());
        return "delete";
    }


    @PostMapping("/deleteBook")
    public String postDelete(@RequestParam("id") int id){
        List<Book> books = new ArrayList<>();
        books = bookRepo.findAll();
        for (Book book : books) {
            if (id == book.getId()) {
                bookRepo.delete(book);
                return "redirect:/delete";
            }
        }
        System.out.println("Error: can't find this title");
        return "redirect:/delete";
    }

}

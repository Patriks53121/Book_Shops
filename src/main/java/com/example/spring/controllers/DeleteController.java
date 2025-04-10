package com.example.spring.controllers;

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

    @GetMapping("/delete")
    public String getDelete(Model model) {

        List<Book> books = AddController.books;

        model.addAttribute("books", books);

        return "delete";
    }


    @PostMapping("/deleteBook")
    public String postDelete(@RequestParam("title") String title){
        ArrayList<Book> books = AddController.books;
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i).getTitle().equals(title)){
                books.remove(i);
                return "redirect:/delete";
            }
        }
        System.out.println("Error: can't find this title");
        AddController.books = books;
        return "redirect:/delete";
    }

}

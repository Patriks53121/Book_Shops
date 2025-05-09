package com.example.spring.controllers;

import com.example.spring.models.Book;
import com.example.spring.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.spring.services.classes.BookService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ChangeController {

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private BookService bookService;

    @GetMapping("/change")
    public String getChange(Model model) {
        model.addAttribute("books", bookRepo.findAll());
        return "change";
    }

    @GetMapping("/change/{title}")
    public String setChanges(@PathVariable String title, Model model) {
        for (Book book : bookRepo.findAll()) {
            if (book.getTitle().equals(title)) {
                model.addAttribute("book", book);
                return "changes";
            }
        }

        return "redirect:/change";
    }

    @PostMapping("/change/{title}")
    public String applyChanges(@PathVariable String title,
                               @RequestParam("originalTitle") String originalTitle,
                               @RequestParam("title") String newTitle,
                               @RequestParam("author") String author,
                               @RequestParam("part") int part,
                               @RequestParam("price") float price,
                               @RequestParam("image") MultipartFile file,
                               @RequestParam("id") int id) {

        String imagePath = "/uploads/default.jpg";
        List<Book> books = bookRepo.findAll();
        for (Book book : books) {
            if(book.getId() == id){
                if(!file.isEmpty()){
                    imagePath = uploadImage(file, newTitle, part);
                    System.out.println("Book added with image ");
                }
                book.setTitle(newTitle);
                book.setAuthor_name(author);
                book.setPart(part);
                book.setBook_price(price);
                book.setImage(imagePath);
                bookService.updateBook(book);
                return "redirect:/change";
            }
        }

        return "redirect:/change";
    }


    public String uploadImage(MultipartFile file, String title, int part) {

        if (file.isEmpty()) {
            return "/uploads/default.jpg";
        }
        try {
            String uploadDir = System.getProperty("user.dir") + "/uploads";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = title + "_" + part + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);

            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return "/uploads/" + fileName;

        } catch (IOException e) {
            e.printStackTrace();
            return "/uploads/default.jpg";
        }
    }

}

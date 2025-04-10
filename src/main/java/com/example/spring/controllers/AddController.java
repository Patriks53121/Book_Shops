package com.example.spring.controllers;

import com.example.spring.models.Book;
import com.example.spring.services.classes.BookService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

import java.util.ArrayList;

@Controller
public class AddController {

    private BookService bookService;
    public static ArrayList<Book> books = new ArrayList<>();


    @GetMapping("/add")
    public String getAdd() {
        return "add";
    }

    @PostMapping("/addBook")
    public String addBook(@RequestParam("title") String title,
                          @RequestParam("author") String author,
                          @RequestParam("part") int part,
                          @RequestParam("price") float price,
                          @RequestParam("image") MultipartFile file) {

        String imagePath = "/uploads/default.jpg";

        Book book;

        if (!file.isEmpty()) {
            imagePath = uploadImage(file, title, part);
            book = new Book();
            book.setTitle(title);
            book.setAuthor_name(author);
            book.setPart(part);
            book.setBook_price(price);
            book.setImage(imagePath);

            bookService.createBook(book);

            System.out.println("Book added with image ");
        }
        else{
            book = new Book();
            book.setTitle(title);
            book.setAuthor_name(author);
            book.setPart(part);
            book.setBook_price(price);
            book.setImage(imagePath);

            bookService.createBook(book);

            System.out.println("Book added without image");
        }

        return "redirect:/add";
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

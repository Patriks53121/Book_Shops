package com.example.spring.controllers;

import com.example.spring.models.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

@Controller
public class ChangeController {

    @GetMapping("/change")
    public String getChange(Model model) {
        model.addAttribute("books", AddController.books);
        return "change";
    }

    @GetMapping("/change/{title}")
    public String setChanges(@PathVariable String title, Model model) {
        for (Book book : AddController.books) {
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
                               @RequestParam("image") MultipartFile file) {

        ArrayList<Book> books = AddController.books;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equals(originalTitle)) {
                if (!newTitle.isEmpty()) {
                    books.get(i).setTitle(newTitle);
                }
                if (!author.isEmpty()) {
                    books.get(i).setAuthor_name(author);
                }
                if (part != 0) {
                    books.get(i).setPart(part);
                }
                if (price != 0) {
                    books.get(i).setBook_price(price);
                }
                if (!file.isEmpty()) {
                    String imagePath = uploadImage(file, newTitle, part);
                    books.get(i).setImage(imagePath);
                }

                AddController.books = books;
                return "redirect:/change";
            }
        }

        return "redirect:/change";
    }



    public String uploadImage(MultipartFile file, String title, int part) {
        ArrayList<Book> books = AddController.books;

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

package com.example.spring.services.classes;

import com.example.spring.models.Book;
import com.example.spring.repositories.BookRepository;
import com.example.spring.services.interfaces.BookInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService implements BookInterface {

    private BookRepository bookRepo;

    @Override
    public Book updateBook(Book book) {
        Book existingBook = bookRepo.findById(book.getId()).orElseThrow(() -> new RuntimeException("Book not found with id: " + book.getId()));
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor_name(book.getAuthor_name());
        existingBook.setPart(book.getPart());
        existingBook.setBook_price(book.getBook_price());
        existingBook.setImage(book.getImage());
        return bookRepo.save(existingBook);
    }

}

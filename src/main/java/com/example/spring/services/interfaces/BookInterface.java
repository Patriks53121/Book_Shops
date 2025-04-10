package com.example.spring.services.interfaces;

import com.example.spring.models.Book;

import java.util.List;

public interface BookInterface {

    Book createBook(Book book);

    List<Book> getAllBook(Book book);

    Book getBookById(int bookId);

    Book updateBook(Book book);

    void deleteBook(int bookId);


}

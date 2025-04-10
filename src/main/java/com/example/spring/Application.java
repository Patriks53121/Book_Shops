package com.example.spring;

import com.example.spring.models.Book;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import java.util.ArrayList;

@SpringBootApplication
public class Application {


	public static void main(String[] args) {

		ArrayList<Book> books = new ArrayList<>();
//		books.add(new Book("ABC", "CBA", 6, 9, "uploads/default.jpg"));
//		books.add(new Book("CBA", "ABC", 9, 6, "uploads/default.jpg"));
//		AddController.books = books;

		SpringApplication.run(Application.class, args);
	}

	@Bean
	public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
		return new HiddenHttpMethodFilter();
	}

}

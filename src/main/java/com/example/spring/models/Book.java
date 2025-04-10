package com.example.spring.models;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="title")
	private String title;

	@Column(name="author_name")
	private String author_name;

	@Column(name="part")
	private int part;

	@Column(name="book_price")
	private float book_price;

	@Column(name="book_available")
	private boolean book_available = true;

	@Column(name="image")
	private String image;

	@ManyToOne
	@JoinColumn(name = "shop_id", referencedColumnName = "id")
	private Shop shop = null;

}
package com.example.spring.models;


import jakarta.persistence.*;

import java.awt.*;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title;
	private String author_name;
	private int part;
	private float book_price;
	private boolean book_avalible;
	private String image;

	@ManyToOne
	@JoinColumn(name = "shop_id", referencedColumnName = "id")
	private Shop shop;

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor_name() {
		return author_name;
	}
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	public int getPart() {
		return part;
	}
	public void setPart(int part) {
		this.part = part;
	}
	public float getBook_price() {
		return book_price;
	}
	public void setBook_price(float book_price) {
		this.book_price = book_price;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return this.id;
	}
	public boolean getBook_avalible() {
		return this.book_avalible;
	}
	public void changeBook_avalible() {
		if(this.book_avalible == false) {
			this.book_avalible = true;
		}
		else if(this.book_avalible == true) {
			this.book_avalible = false;
		}
	}
	public String getImage(){
		return this.image;
	}
	public void setImage(String image){
		this.image = image;
	}
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Book(String title, String author_name, int part, float price, String image) {
		this.title = title;
		this.author_name = author_name;
		this.part = part;
		this.book_price = price;
		this.image = image;
	}
	public Book(String title, String author_name, int part, float price) {
		this.title = title;
		this.author_name = author_name;
		this.part = part;
		this.book_price = price;
	}
	public Book(String title, int part, float price) {
		this.title = title;
		this.part = part;
		this.book_price = price;
	}
	public Book(String title, float price) {
		this.title = title;
		this.part = 1;
		this.book_price = price;
	}
	public Book(){}







}
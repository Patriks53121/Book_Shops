package com.example.spring.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity
@Table(name = "shops")
public class Shop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="shop_name")
	private String shop_name;

	@Column(name="shop_address")
	private String shop_address;

	@Column(name="shop_phone_number")
	private String shop_phone_number;

	@OneToMany(mappedBy = "book_id", cascade = CascadeType.ALL, orphanRemoval = true)
	private ArrayList<Book> book_library = new ArrayList<>();

	@OneToMany(mappedBy = "transfer_id", cascade = CascadeType.ALL, orphanRemoval = true)
	private ArrayList<Transfer> transfer_history = new ArrayList<>();


}
package com.example.spring.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;

@Data
@Entity
@Table(name = "banks")
public class Bank{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="card_number")
	private String card_number;

	@Column(name="money")
	private int money = 0;

	@OneToMany(mappedBy = "transfer", cascade = CascadeType.ALL, orphanRemoval = true)
	private ArrayList<Transfer> transfer = new ArrayList<>();


}
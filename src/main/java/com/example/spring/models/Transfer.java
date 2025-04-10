package com.example.spring.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@Entity
@Table(name = "transfers")
public class Transfer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="time")
	private LocalDateTime time;

	@Column(name="completed")
	private boolean completed;

	@Column(name="cost")
	private float cost;

	@ManyToOne
	@JoinColumn(name = "shop_id", referencedColumnName = "id")
	private Shop shop;

	@ManyToOne
	@JoinColumn(name = "bank_id", referencedColumnName = "id")
	private Bank bank;

	@OneToMany(mappedBy = "transfer", cascade = CascadeType.ALL, orphanRemoval = true)
	private static ArrayList<Transfer> allTranfers = new ArrayList<>();
}
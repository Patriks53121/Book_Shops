package com.example.spring.models;

import com.example.spring.models.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Table(name = "transfers")
public class Transfer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	private float cost;

	@ManyToOne
	@JoinColumn(name = "shop_id", referencedColumnName = "id")
	private Shop shop;

	private LocalDateTime time;
	private boolean completed;

	@ManyToOne
	@JoinColumn(name = "bank_id", referencedColumnName = "id")
	private Bank bank;

	@OneToMany(mappedBy = "transfer", cascade = CascadeType.ALL, orphanRemoval = true)
	private static ArrayList<Transfer> allTranfers = new ArrayList<>();

	public float getCost() {
		return cost;
	}
	public Shop getShop() {
		return shop;
	}
	public LocalDateTime getTime() {
		return time;
	}
	private void setTime() {
		this.time = LocalDateTime.now();
	}
	public int getId() {
		return this.id;
	}
	private void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return this.user;
	}
	public boolean getCompleted() {
		return this.completed;
	}
	public void setCompleted(boolean complete ) {
		this.completed = complete;
	}
	public Transfer(User user, float cost, Shop shop) {
		this.user = user;
		this.cost = cost;
		this.shop = shop;
		setTime();
	}
	public void makeTransfer(User user, float cost, Shop shop) {
		if(user.getBank().getMoney() >= cost) {
			Transfer transfer = new Transfer(user, cost, shop);
			transfer.setCompleted(true);
			user.getBank().addTransfers(transfer);
			shop.addTransfer_history(transfer);
			allTranfers.add(transfer);
		}else {
			System.out.println("Not enough money");
			Transfer transfer = new Transfer(user, cost, shop);
			transfer.setCompleted(false);
			user.getBank().addTransfers(transfer);
			shop.addTransfer_history(transfer);
			allTranfers.add(transfer);

		}

	}



}
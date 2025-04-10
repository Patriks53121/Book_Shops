package com.example.spring.models;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "banks")
public class Bank{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String card_number;
	private int money = 0;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private final User user;

	@OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, orphanRemoval = true)
	private ArrayList<Transfer> transfers = new ArrayList<>();

	public String getCard_number() {
		return card_number;
	}
	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public void addMoney(int money){
		this.money = this.money + money;
	}
	public void removeMoney(int money) {
		if(this.money<=0) {
			System.out.println("Declined");
		}else {
			this.money = this.money - money;
		}
	}
	public ArrayList<Transfer> getTransfers() {
		return transfers;
	}
	public void addTransfers(Transfer transfer) {
		transfers.add(transfer);
	}
	public Bank(String card_number, int money, ArrayList<Transfer> transfers, User user) {
		this.card_number = card_number;
		this.money = money;
		this.transfers = transfers;
		this.user = user;
	}
	public Bank(String card_number, int money, User user) {
		this.card_number = card_number;
		this.money = money;
		this.user = user;
	}

}
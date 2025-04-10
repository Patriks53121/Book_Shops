package com.example.spring.models;


import jakarta.persistence.*;

import java.util.Random;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	private String surname;
	private int age;

	@Column(name = "phone_number")
	private String phone_number;

	private String address;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bank_id", referencedColumnName = "id")
	private Bank bank;



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Bank getBank() {
		return bank;
	}
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User(String name, String surname, int age, String phone_number, String address, Bank bank) {
		super();
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.phone_number = phone_number;
		this.address = address;
		this.bank = bank;
	}
	public User(String name, String surname, int age, String phone_number, String address) {
		Random random = new Random();
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.phone_number = phone_number;
		this.address = address;
		this.bank = new Bank(String.valueOf(random.nextInt(100,1000)), 100, this);
	}
	public User(){}



}
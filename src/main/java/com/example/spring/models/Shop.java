package com.example.spring.models;


import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "shops")
public class Shop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String shop_name;
	private String shop_address;
	private String shop_phone_number;

	@OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, orphanRemoval = true)
	private ArrayList<Book> book_library = new ArrayList<>();

	private int book_id;
	protected int book_id_counter = 1;

	@OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, orphanRemoval = true)
	private ArrayList<Transfer> transfer_history = new ArrayList<>();


	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public String getShop_address() {
		return shop_address;
	}
	public void setShop_address(String shop_address) {
		this.shop_address = shop_address;
	}
	public String getShop_phone_number() {
		return shop_phone_number;
	}
	public void setShop_phone_number(String shop_phone_number) {
		this.shop_phone_number = shop_phone_number;
	}
	public ArrayList<Book> getBook_library() {
		return book_library;
	}
	public void addBook(Book book, int price) {
		book.setId(book_id);
		book_id_counter++;
		book.setBook_price(price);
		book_library.add(book);
	}
	public void removeBook(int book_id) {
		for (int i = 0; i < this.book_library.size(); i++) {
			if (book_id == this.book_library.get(i).getId()) {
				this.book_library.remove(i);
				break;
			}
		}
	}
	public ArrayList<Transfer> getTransfer_history() {
		return this.transfer_history;
	}
	public void addTransfer_history(Transfer transfer) {
		transfer_history.add(transfer);
	}
	public void showTransfer_history() {
		for (int i = 0; i < this.transfer_history.size(); i++) {
			System.out.println("-------");
			System.out.println("Cost: " + this.transfer_history.get(i).getCost());
			System.out.println("Shop: " + this.transfer_history.get(i).getShop());
			System.out.println("Time: " + this.transfer_history.get(i).getTime());
			System.out.println("Id: " + this.transfer_history.get(i).getId());
		}
		System.out.println("-------");
	}
	public Shop(String name, String address, String phone, ArrayList<Book> library, ArrayList<Transfer> transfer, int id, int id_counter) {
		this.shop_name = name;
		this.shop_address = address;
		this.shop_phone_number = phone;
		this.book_library = library;
		this.transfer_history = transfer;
		this.book_id = id;
		this.book_id_counter = id_counter;

	}
	public Shop(String name, String address, String phone, ArrayList<Book> library) {
		this.shop_name = name;
		this.shop_address = address;
		this.shop_phone_number = phone;
		this.book_library = library;

	}
	public Shop(String name, String address, String phone) {
		this.shop_name = name;
		this.shop_address = address;
		this.shop_phone_number = phone;

	}
	public Shop(){}
	public void buy(ArrayList<Book> books, User user) {
		System.out.println("User: " + user.getName());
		ArrayList<Book> avalible_books = new ArrayList<>();
		for (int i = 0; i < books.size(); i++) {
			for (int j = 0; j < book_library.size(); j++) {
				if (books.get(i) == book_library.get(j) && book_library.get(j).getBook_avalible()) {
					avalible_books.add(books.get(i));
					book_library.get(j).changeBook_avalible();
				} else if (books.get(i) == book_library.get(j) && !book_library.get(j).getBook_avalible()) {
					System.out.println("Book " + books.get(i).getTitle() + ", it's not avalible.");
				} else if (j == book_library.size() - 1) {
					System.out.println("Can not find this book: " + books.get(i).getTitle() + ".");
				}
			}
		}
		float price = 0;

		for (int i = 0; i < avalible_books.size(); i++) {
			price = price + avalible_books.get(i).getBook_price();
		}
		Shop shop = new Shop(this.shop_name, this.shop_address, this.shop_phone_number, this.book_library, this.transfer_history, this.book_id, this.book_id_counter);
		Transfer transfer = new Transfer(user, price, shop);
		transfer.makeTransfer(user, price, shop);
		this.book_library = shop.book_library;
		this.transfer_history = shop.transfer_history;
		this.book_id = shop.book_id;
		this.book_id_counter = shop.book_id_counter;
		if (shop.transfer_history.getLast().getCompleted()) {
			System.out.println("Buy success");
		} else {
			System.out.println("Buy declined");
		}

	}
	public void buy(Book book, User user) {
		System.out.println("User: " + user.getName());
		for (int i = 0; i < book_library.size(); i++) {
			if (book == book_library.get(i)) {
				float price = book_library.get(i).getBook_price();
				Shop shop = new Shop(this.shop_name, this.shop_address, this.shop_phone_number, this.book_library, this.transfer_history, this.book_id, this.book_id_counter);
				Transfer transfer = new Transfer(user, price, shop);
				transfer.makeTransfer(user, price, shop);
				this.book_library = shop.book_library;
				this.transfer_history = shop.transfer_history;
				this.book_id = shop.book_id;
				this.book_id_counter = shop.book_id_counter;
			} else if (i == book_library.size() - 1) {
				System.out.println("Can not find this book: " + book.getTitle() + ".");
			}
		}
	}


}
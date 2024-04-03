package CRUD_Programs.BookManageMentSystem;

import java.util.ArrayList;
import java.util.List;

class Customer_Manager implements Customers_Consequential <Book> {
	
	private List<Book>borrowedBook;
	private List<Customer> customer_list;
	Customer_Manager(){
		this.borrowedBook = new ArrayList<>();
		this.customer_list = new ArrayList<>();
	}

	@Override
	public void borrowBook(Book book) {
		for( Customer customer : customer_list) {
		double budget =  customer.getBudget();
		if( book.isAvaliable()&& budget >= book.getBook_cost()) {
			borrowedBook.add(book);
			 budget -= book.getBook_cost();
			 customer.setBudget(customer.getBudget() - book.getBook_cost());
			 book.setAvaliable(false);
			 System.out.println(customer.getName() + " borrowed the book: " + book.getTitle());
		}
		else {
			  System.out.println("Unable to borrow the book: " + book.getTitle());
		}
	 }
  }

	@Override
	public void returnBook(Book book) {
		for( Customer customer : customer_list) {
		double budget =  customer.getBudget();
		if( borrowedBook.contains(book)) {
			borrowedBook.remove(book);
			budget += book.getBook_cost();
			book.setAvaliable(true);
			 System.out.println(customer.getName() + " returned the book: " + book.getTitle());
		}
		else {
			   System.out.println(customer.getName() + " didn't borrow this book: " + book.getTitle());
		}
	 }
   }

	@Override
	public void ViewBorrowedBook() {
		for( Customer customer : customer_list) {
        if (borrowedBook.isEmpty()) {
            System.out.println(customer.getName() + " has not borrowed any books.");
        } else {
            System.out.println(customer.getName() + "'s Borrowed Books:");
            for (Book book : borrowedBook) {
                System.out.println(book);
            }
        }
	}
    }

	@Override
	public Customer getCustomerByName(String name ) {
		for( Customer customer : customer_list) {
			if( customer.getName().equalsIgnoreCase(name)) {
				return customer;
			}
		}
		return null;
	}

	@Override
	public void addCustomer(Customer customer ) {
		
		customer_list.add(customer);
		System.out.println(customer.getName() + " added successfully ");
		System.out.println("\n-----------------");
		
	}
}

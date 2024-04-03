package CRUD_Programs.BookManageMentSystem;

interface Customers_Consequential <T>{
	public void borrowBook(T item);
	public void returnBook(Book book);
	public void ViewBorrowedBook();
	public Customer getCustomerByName(String name);
	public void addCustomer( Customer customer );
}

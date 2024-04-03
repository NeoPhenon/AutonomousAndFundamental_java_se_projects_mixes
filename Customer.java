package CRUD_Programs.BookManageMentSystem;

class Customer {
	private String name ;
	private String password;
	private int id ;
	private double budget;
	public Customer(String name, String password, int id, double budget) {
		this.name = name;
		this.password = password;
		this.id = id;
		this.budget = budget;
		
	}
	Customer(){}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getBudget() {
		return budget;
	}
	public void setBudget(double budget) {
		this.budget = budget;
	}
}

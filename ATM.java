package ATM_managementSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
   //YE ZAW WIN(NEO):
public class ATM {
	
	public static final Scanner sc = new Scanner(System.in);
	
	public static double balance;
	List<User> list; // briefly synonymous with User[]list = new User[5]; // object array 
	ATM(){
		System.out.println(" Please Enter Your Punctilious Initial Balance --");
		this.balance = sc.nextDouble();
		this.list = new ArrayList<>();
		
		  Initial_Account();
		
			Login_process();
	}

	public double getBalance(){
		return balance;
	}
	public void setBalance(double balance ) {
		this.balance = balance;
	}
	

	public void Initial_Account() {
		User pos_one = new User("Neon",112233);
		User pos_two = new User("Neli",223344);
		list.add(pos_one);
		list.add(pos_two);
	}

	public void Login_process() {
		
		boolean isLoggedIn = false;
		
	while( true ) {
			
		if( !isLoggedIn ) {
			System.out.println(" Pls Enter the name ");
			String name = sc.next();
			System.out.println("Pls Enter the password");
			int password = sc.nextInt();
			
			isLoggedIn = performLogin(name , password ); 
			if(!isLoggedIn) {
				 System.out.println("Login failed. Do you want to create an account? (yes or no)");
				 sc.nextLine(); // consume nextLine;
				 String str_choice = sc.nextLine();
				 if( "yes".equalsIgnoreCase(str_choice)) {
					 CreateUser();
					 isLoggedIn = true;			 
				 }
				 else {
					 System.out.println("Thanks for supporting our ATM system.");
					 break;
				 }
			    }
			}
		else {
			menu_process();
	}
		}
		
	}
	public void menu_process() {
		
		int choice = show_menu();
		
	if( choice == 0 ) {
		System.out.println("Thanks for supporting Our ATM system .");
		System.exit(0);
		sc.close();
	}
	switch(choice) {
	case 1:
		Deposit_process();
		break;
	case 2 : 
		WithDrawl_process();
		break;
	case 3: 
		check_balance();
		break;
	case 4:
		change_password(); // as we have setter method to retrieve and set apart new things
		break;
	case 5:
		viewAccountDetails();
		break;
	default :
		System.out.println(" Discourtaneous choice , Try again! ");
	}
		
	}

	public void viewAccountDetails() {
		sc.nextLine(); //  to eradicate being malodorously stuck in the menu.
		System.out.println(" Pls Enter Your Name ");
		String name = sc.nextLine();
		
		for( User user : list ) {
			if(user.getName().equals(name)) {
				display_userDetails(user);
				return;
			}
		}
		System.out.println("User not found. Please check the name you entered.");
	}

	public void display_userDetails(User user) {
		 System.out.println("-------- ATM -----------");
			System.out.println(" Name :"+user.getName());
			System.out.println(" Password : " + user.getPassword());
			System.out.println(" Balance: $$ "+ getBalance());
			System.out.println("-----------------------");
	}

	public void change_password() {
		System.out.println(" Enter the current password ");
		int current_password = sc.nextInt();
		
		boolean found_pass = false;
		for( User user : list ) {
			if( user.getPassword() == current_password ) {
				found_pass = true;
				System.out.println(" Enter the new password ");
				int new_password = sc.nextInt();
				user.setPassword(new_password);
				System.out.println("password changed flourishingly");
				System.out.println("-----------------");
				break;
			}
		}
	}
	public boolean performLogin(String name, int password) {
		for( User user : list ) {
			if( user.getName().equals(name) && user.getPassword() == password ) {
				System.out.println(" Login successfully ");
				System.out.println("----------------------");
				return true;
			}
		}
		System.out.println(" Login failed , Erroneous credentials. ");
		System.out.println();
		return false;
	}
	public void check_balance() {
		
		double balance = getBalance();
		System.out.println(" Your Account Balance : $$ "+ balance );
		System.out.println();
	}
	public void CreateUser() {
		System.out.println(" Pls Enter the name ");
		String name = sc.next();
		
		System.out.println("Pls Enter the password");
		int password = sc.nextInt();
		
		User user = new User(name , password);
		// then we just have to branch them out to our(list) instead of using daunting obj array ):
		list.add(user); // acting like memory space in this program 
		System.out.println("User Created Successfully");
		System.out.println("-----------------");
	}
	public void Deposit_process(){
		System.out.println(" Enter your amount to get across to deposit ");
		double amount = sc.nextDouble();
		Deposit deposit = new Deposit(amount);
		deposit.calculation_process(this); // refers to atm class
	}
	public void WithDrawl_process(){
		System.out.println(" Enter your amount to get across to Withdrawl ");
		double amount = sc.nextDouble();
		WithDraw draw = new WithDraw(amount);
		draw.calculation_process(this);
	}
	
	public int show_menu() {
		System.out.println("0.Exit");
		System.out.println("1.Deposit");
		System.out.println("2.WithDraw");
		System.out.println("3.Check Balance");
		System.out.println("4.Change password");
		System.out.println("5.Display Accounts Detils ");
		// unabashedly affordable to branch out more methods like transactionHistory;
		int choice = sc.nextInt();
		return choice;
	}

	public static void main(String[] args ) {
		new ATM();
	}
}
class Transaction{
	private double amount ;
	Transaction(double amount){
		this.amount = amount;
	}
	
	public double getAmount(){
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public void calculation_process(ATM atm){}

	
}
class Deposit extends Transaction{

	Deposit(double amount) {
		super(amount);
	}
	
	public void calculation_process(ATM atm) {
		// stung up atm class to get accumstomed with getBalance method
		if( getAmount() > 0 ) {
			atm.setBalance(atm.getBalance() + getAmount());
			System.out.println(" Deposit unsparingly successful. New Balance = " + atm.getBalance());
		}
		else {
			System.out.println(" Insufficient fund (or) Invalid amount for deposit ");
		}
	}
}
class WithDraw extends Transaction{

	WithDraw(double amount) {
		super(amount);
	}
	@Override
	public void calculation_process(ATM atm ) {
		if( getAmount() > 0 && getAmount() <= atm.getBalance() ){
			atm.setBalance(atm.getBalance() - getAmount() );
			System.out.println(" Withdrawl completely successful . New Balance = " + atm.getBalance());
		}
		// if we bumped into tenuous balance then there is nothing we can do ->
		else {
			System.out.println("Invalid amount for withdrawl ");
		}
	}
}
class User  {
	private String name ;
	private int password;
	
	User(String name , int password ){
		this.name = name;
		this.password = password;
	}
	User(){}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPassword() {
		return this.password;
	 }
	
	public void setPassword(int password) {
		this.password = password;
	 }
}
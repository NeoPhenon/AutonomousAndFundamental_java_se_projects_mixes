package CrazyRobot_Program;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CrazyRobotProgram {
	
	public static final String user_name = "User";
	
	public static final String robot_name = "CrazyBot";
	
	public static final String user_email = "neo@gmail.com";
	
	public static final String user_password = "11111";
	
	public static final Scanner sc = new Scanner(System.in);
	
	
	CrazyRobotProgram(){
		
		UserCreditial();
		
		Dicitionary dictionary = new Dicitionary();
		
		User user = new User(user_name , dictionary );
		
		CrazyBot robot = new CrazyBot(robot_name , dictionary );
		
		chatBox(user , robot );
		
	}
	public void UserCreditial() {
		
		Map<String,String> login_map = new HashMap<>();
		
		Map<String,String>welcome_map = new HashMap<>();
		
		System.out.println("Please Enter your name ");
		String User_name = sc.nextLine();
		
		welcome_map.put(user_email , User_name );
		
		// key -> email , value -> password 
		login_map.put(user_email , user_password);
		
		
		boolean isLogged_in = false;
		
		while( !isLogged_in ) {
		
		System.out.println(" Please Enter the Email ");
		String input_email = sc.nextLine();
		
		System.out.println(" Please Enter the password ");
		String input_password = sc.nextLine();
		
		if(login_map.containsKey(input_email) && login_map.get(input_email).equals(input_password)) {
			isLogged_in = true;
			System.out.println("Login successful!");
			System.out.println("-------------------");
			
			if(welcome_map.containsKey(input_email)) {
				System.out.println(" Welcome : " +welcome_map.get(input_email)+"!"+" To Mesmerizing CrazyBot System.");
				 System.out.println(" ---------------- "); 
				 System.out.println();
			}
			
		}
		else {
			System.out.println("Erroenous email and password . Just Try Again ");
			System.out.println("-------------------");
			isLogged_in = false;
		}
		
		}
		
	}
	public void chatBox( User user , CrazyBot robot ) {
		String previousUser_question = null ;
		
		while( true ) {
			 System.out.print("User: ");
			String user_question = sc.nextLine();
			
			if( user_question.equals("exit") || user_question.equals(String.valueOf(0))) {
				System.exit(0);
				break;
			}
			
			String bot_response = robot.respond(user_question);
			
			 System.out.println("CrazyBot: " + bot_response);
			 
		 if( previousUser_question != null && !previousUser_question.isEmpty() ){
				 handleUser_response( robot , user_question , previousUser_question );
				 previousUser_question = "";
			   }
	  else {
			 if( !bot_response.equals(user.user_response[0])) {
				 System.out.println(" How should I respond ligitimately to " +" >>"+ user_question + "<<");
				 
				 String userResponse = sc.nextLine();
				 if(!userResponse.isEmpty()) {
					 handleUser_response( robot , user_question , userResponse );
				 }
			  }
			 else {
				 previousUser_question = user_question;
			 }
			 
			 }
		}
		// I lashed up precisely 2 hours to wrench out of this multifarious logic
		
		sc.close();
		
	}

	public void handleUser_response(CrazyBot robot, String user_question, String userResponse) {
		 robot.Userinstruction(user_question, userResponse);
		 robot.SayThankYou();
	}
	
	public static void main(String[] args ) {
		new CrazyRobotProgram();
	}
}

class Dicitionary{
	public String questions[]; // List<String> questions_list = new ArrayList<>();
	public String responses[]; // List<String> responseList = new ArrayList<>();
	
	// to impeccably store the indispensable questions and responses that the user put out ;
	// As a question and response Structure 
	Dicitionary(){
		this.questions = new String[10];
		this.responses = new String[10];
	}

	 public void Robot_learning_process(String question , String response ) {
		for( int i = 0 ; i < questions.length ; ++i ) {
			if( questions[i] == null && responses[i] == null ) {
				questions[i] = question;
				responses[i] = response;
				break;
			}
		}
	}
	 
	public String searchFor_words(String question) {
		for( int i = 0 ; i < questions.length ; ++i ) {
			String current_question = questions[i];
			
			if(current_question != null && current_question.equalsIgnoreCase(question)) {
				
				return responses[i];
			}
		}
		return null;
	}
}

class User{
	
	  String name ;
	  protected Dicitionary dictionary ;
	  public String[] user_response ;
	  public String[] robot_response;
	  
	User(String name ,Dicitionary dictionary ){
		this.name = name;
		this.dictionary = dictionary ;
		this.user_response = new String[1];
		this.robot_response = new String[1];
		
		user_response[0] = "";
	}
	
	User(){
		
	}
	
	public void Userinstruction(String question , String response ) {
		dictionary.Robot_learning_process(question, response);
	}
	
	public String respond( String question ) {
		String userResponse = dictionary.searchFor_words(question);
		
		if( userResponse != null ) {
			return userResponse;	
		}
		else {
			return user_response[0];
		}
	}
}

class CrazyBot extends User {
	
public static final String robot_response[] = {"I am Crazy Bot \n " , "I don't know the answer to that. Can you teach me? \n "};

	CrazyBot( String name , Dicitionary dictionary){
		super(name,dictionary);
	}
	
	public void SayThankYou() {
		System.out.println(" Thanks for teaching me. \n ");
	}
	
	@Override
	public String respond( String question ) {
		String response = dictionary.searchFor_words(question);
		
		if( response != null ) {
			return response;
		}
		else if( question.equals("Hi") || question.equals("Hello CrazyBot")) {
			return robot_response[0];
		}
		else {
			return robot_response[1];
		}
	}
	
}




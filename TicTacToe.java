import java.util.Scanner;
import java.util.Random;
public class TicTacToe{
	public static void main(String[]args){
		char[][]board = {{' ' ,' ' ,' '},
				 {' ' , ' ',' '},
				 {' ' , ' ',' '}};	
			Scanner s = new Scanner(System.in);

		 	print_board(board);
			while(true){
			player_turn(board,s);
			if(isGameFinished(board)){
				break;
			}
			computer_turn(board);
			if(isGameFinished(board)){
				break;
			}
			print_board(board);

			}
			
		}
		private static boolean isGameFinished(char[][] board ){
		
			if(hasCompetitorsWon(board , 'X')){
		 	print_board(board);
			System.out.println(" Player Win! ");
			return true;
			}
			
			if(hasCompetitorsWon(board , 'O')){
			print_board(board);
			System.out.println(" Computer wins! ");
			return true;
			
				}
		
			
		for( int i = 0 ; i < board.length ; ++i ){
			for( int j = 0 ; j < board[i].length ; ++j ){
				if(board[i][j] == ' '){
					return false;
				}
				}
			}
			print_board(board);
			System.out.println(" Game is too mesmerizing and cherry on top!! ");
			return true;
		
			
		}
		private static boolean hasCompetitorsWon(char[][]board , char symbol ){

		if( (board[0][0] == symbol  && board[0][1] == symbol   && board[0][2] == symbol ) || 
		    (board[1][0] == symbol  && board[1][1] == symbol  && board[1][2] == symbol )||
		    (board[2][0] == symbol  && board[2][1] == symbol  && board[2][2] == symbol ) ||
		    
 		    (board[0][0] == symbol  && board[1][0] == symbol  && board[2][0] == symbol )|| 
		    (board[0][1] == symbol  && board[1][1] == symbol  && board[2][1] == symbol )||
		    (board[0][2] == symbol  && board[1][2] == symbol  && board[2][2] == symbol )||
			
		   (board[0][0] == symbol  && board[1][1] == symbol  && board[2][2] == symbol ) || 
		   (board[0][2] == symbol  && board[1][1] == symbol  && board[2][0] == symbol ) ){

			print_board(board);
			System.out.println(" player win!! ");
			return true ;
		}
			return false;
		}
		private static void computer_turn(char[][]board){

		int computer_move;
			while(true){
			Random rand = new Random();
			 computer_move = rand.nextInt(9)+1;
			if(isAccessible_move(board , computer_move )){
				break;
			} 
		}
			System.out.println(" Computer chosen " + computer_move);
			
			place_to_move(board , Integer.toString(computer_move),'O');
		}
		private static void place_to_move(char[][]board , String position , char symbol ){
				switch(position ){
				case "1":
					board[0][0] = symbol ;
				break;
				case "2":
					board[0][1] = symbol ;
				break;
				case "3":
					board[0][2] = symbol ;
				break;
				case "4":
					board[1][0] = symbol ;
				break;
				case "5":
					board[1][1] = symbol ;
				break;
				case "6":
					board[1][2] = symbol ;
				break;
				case "7":
					board[2][0] = symbol ;
				break;
				case "8":
					board[2][1] = symbol ;
				break;
				case "9":
					board[2][2] = symbol ;
				break;
				
				default:
				System.out.println(" Wrong input!! be enigmatic, man ");
		
			}
		}
		private static boolean isAccessible_move(char[][]board , int position){

			switch(position){  
				case 1:
				   	return (board[0][0] == ' ');
				
				case 2:
					return (board[0][1] == ' ');
				
				case 3:
					return (board[0][2] == ' ');
				
				case 4:
					return (board[1][0] == ' ');
				
				case 5:
					return (board[1][1] == ' ');
				
				case 6:
					return (board[1][2] == ' ');
				
				case 7:
					return (board[2][0] == ' ');
				
				case 8:
					return (board[2][1] == ' ');
				
				case 9:
					return (board[2][2] == ' ');
				
				
				default:
				System.out.println(" Wrong input!! pathetic ");
		
			}
				return false;
		}
		private static void player_turn(char[][]board , Scanner s ){
			String input;

			while(true){
		try{
			System.out.println(" Enter your move . 1 to 9 ");
			input = s.nextLine();
			if(isAccessible_move(board , Integer.parseInt(input))){
				break;
			}
			else {
				System.out.println(input + " Is not erroneously valid move ");
			}
		  }
			catch(NumberFormatException nfe ){
				System.out.println(" Wrong input , so preposeterous , try again ");
			}

		}
			place_to_move( board , input , 'X');
			
		}
		private static void print_board(char[][]board){

			System.out.println(board[0][0] + "|" + board[0][1] + "|" + board[0][2]);
			System.out.println("-+-+-");
			System.out.println(board[1][0] + "|" + board[1][1] + "|" + board[1][2]);
			System.out.println("-+-+-");
			System.out.println(board[2][0] + "|" + board[2][1] + "|" + board[2][2]);
		}
	}
import java.util.Scanner;

public class Program {
	
	
	// Main loop of our program
	public static void main(String[] args){
		
		String inputStr = "";
		Scanner input = new Scanner(System.in);
		Logger l = Logger.getInstance();	
		Logger l2 = Logger.getInstance();
		
		System.out.println(l.equals(l2));
	
		
		while (true){
			System.out.println("Select from the following options or type 'quit' to exit\n "
					+ "1) Enter user id and movie id\n "
					+ "2) Enter user id and threshold \n");
			
			inputStr = input.nextLine();
			
			try {
				
				if (inputStr.toLowerCase().equals("quit")){
					input.close();
					break;
				}
				
				if (Integer.parseInt(inputStr) == 1){
					System.out.println("Enter id and movie");
					inputStr = input.nextLine();
					l.appendToFile(inputStr);
				}
				
				else if (Integer.parseInt(inputStr) == 2){
					System.out.println("Enter id and threshold");
					inputStr = input.nextLine();
					l.appendToFile(inputStr);
				}
				
				else {
					System.out.println("Invalid input, please try again");
				}	
				
			}
			catch (NumberFormatException e) {
				System.out.println("Please enter either 1 or 2 to select a valid option from the menu");
			}
			
		}

	}
	
}

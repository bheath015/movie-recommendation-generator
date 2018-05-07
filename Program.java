import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * The presentation layer of our program, contains a menu
 * for the user to select different options from. Displays 
 * predicted movie ratings and recommendations.
 */
public class Program implements InterObserver {
	
	Predictor pred;
	String userFile;
	String movieFile;
	int currentUFLength;
	int currentMFLength;
	
	public Program () {
		userFile = System.getProperty("user.dir") + "/ratings2.dat";
		movieFile = System.getProperty("user.dir") + "/movies.dat";
		System.out.println("Loading movies into database...");
		pred = new Predictor(userFile, movieFile);
	}
	
	public void updateReader() {
		pred = new Predictor(userFile, movieFile);
	}

	// Main loop of our program
	public void run(){
		
		String inputStr = "";
		Scanner input = new Scanner(System.in);
		Logger l = Logger.getInstance();	
		
		ReaderChecker rc = new ReaderChecker();
		rc.add(this);
		currentUFLength = rc.getLength(userFile);
		currentMFLength = rc.getLength(movieFile);
	
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
				
				// If we want an expected rating for a movie from a specific user 
				if (Integer.parseInt(inputStr) == 1){
					
					try {
						System.out.println("Enter user id: ");
						int userid = input.nextInt();
						
						System.out.println("Enter movie id: ");
						int movieid = input.nextInt();
						
						l.appendToFile(String.format("User ID: %d, Movie ID: %d\n", userid, movieid));
						
						double expectedR = pred.expectedRating(userid, movieid);
						System.out.printf("The expected rating of user %d for movie %d is %.2f\n", userid, movieid, expectedR);
					}
					
					catch (NumberFormatException e){
						System.out.println("Must enter a valid integer id");
					}	
				}
				
				else if (Integer.parseInt(inputStr) == 2){
					try {
						System.out.println("Enter user id: ");
						int userid = input.nextInt();
						
						System.out.println("Enter threshold: ");
						int threshold = input.nextInt();
						
						l.appendToFile(String.format("User ID: %d, Threshold: %d\n", userid, threshold));
						
						ArrayList<String> topMovies = pred.getTopMovies(userid, threshold);
						System.out.printf("The top %d recommended movies for user %d are:\n", threshold, userid);

						for (int i=0; i < topMovies.size(); i++){
							System.out.println(topMovies.get(i));
						}						
						
						System.out.println();
						
						rc.checkChanges(movieFile, currentMFLength);
						rc.checkChanges(userFile, currentUFLength);
					}
					
					catch (NullPointerException npe) {
						System.out.println("That user ID is not valid!");
					}
					
					catch (NumberFormatException e){
						System.out.println("Must enter a valid integer id!");
					}	
					
					catch (InputMismatchException ime) {
						System.out.println("That is not a valid integer!");
					}
				}
				
				else {
					System.out.println("Invalid input, please try again!");
				}	
				
			}
			catch (NumberFormatException e) {
				System.out.println("Please enter either 1 or 2 to select a valid option from the menu");
			}
			
		}

	}

	@Override
	public void update() {
		updateReader();
	}
	
}

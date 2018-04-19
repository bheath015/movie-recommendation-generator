import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
 * Singleton logger that prevents creation of more than
 * one instance of this class. Appends all user input to
 * a file without overwriting or clearing existing data.
 */
public final class Logger {
	
	private final static String fpath = System.getProperty("user.dir") + "/logs.txt";
	private static Logger instance;
	
	// constructor
	public Logger(){
		if (instance != null){
			//Prevent multiple instances from being created
			throw new IllegalStateException("Cannot instantiate a new singleton instance of Logger");
		}
		this.createLogFile();
	}
	
	
	// returns existing instance without creating a new one
	public static Logger getInstance(){
		if (instance == null){
			instance = new Logger();
		}
		return instance;	
	}
	
	// checks if file exists
	private boolean fileExists(){
		File f = new File(fpath);
		
		if(f.exists() && !f.isDirectory()) { 
			System.out.println("File already exists");
			return true;
		}
		return false;
	}
	
	// creates log file only if the file does not exist
	public void createLogFile(){
		if (fileExists()){
			return;
		}
		
		else {
			System.out.println("Creating file");
			File f = new File(fpath);
			try {
				f.createNewFile();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// adds lines of user input to file
	public void appendToFile(String line){
		// only does anything if file not found
		createLogFile();
		
		try(FileWriter fw = new FileWriter(fpath, true);
			BufferedWriter bw = new BufferedWriter(fw);){
			
			bw.write(line + "\n");
		}
		
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args){
		String inputStr = "";
		Scanner input = new Scanner(System.in);
		Logger l = new Logger();
		
		while (true){
			System.out.println("Select from the following options or type 'quit' to exit\n "
					+ "1) Enter user id and movie id\n "
					+ "2) Enter user id and threshold \n");
			
			inputStr = input.nextLine();
			
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

	}
}

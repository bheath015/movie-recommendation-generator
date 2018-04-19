import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*
 * Singleton logger that prevents creation of more than
 * one instance of this class. Appends all user input to
 * a file without overwriting or clearing existing data.
 */
public final class Logger {
	
	private final static String fpath = System.getProperty("user.dir") + "/logs.txt";
	public static Logger instance;
	
	// constructor
	private Logger(){
		instance = this;
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
}

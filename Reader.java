/*
 * This abstract class is the foundation of reader classes.
 * It dictates that all readers must make lists of the contenst of
 * the files that they read in
 */
import java.util.ArrayList;
import java.util.List;

public abstract class Reader {
	String fileName;
	List<String> output;
	
	/*
	 * The constructor establishes necessary instance variables
	 */
	public Reader(String fileNameInput) {
		fileName = fileNameInput;
		output = new ArrayList<String>();
	}
	
	/*
	 * All readers must read in a file
	 */
	public abstract List<String> read();

}

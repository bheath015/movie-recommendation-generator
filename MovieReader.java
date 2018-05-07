/*
 * This class is an extension of the abstract
 * reader class and reads in movie files. It also checks
 * to see if there are any additions to the movie file and
 * can update classes that observe instances of this class
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MovieReader extends Reader {

	/*
	 * The constructor initiates the loop to check
	 * for changes in the file
	 */
	public MovieReader(String fileName) {
		super(fileName);
	}
	
	/*
	 * We read in lines that adhere to correct regex
	 * patterns and return the list of lines
	 */
	public List<String> read() {
		File inputFile = new File(super.fileName);
		Scanner in;
		try {
			in = new Scanner(inputFile);
			while (in.hasNextLine()) {
				String test = in.nextLine();
				String regex = "\\d*::.*::.*";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(test);
				if (matcher.matches()) {
					super.output.add(test);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return super.output;
	}
}

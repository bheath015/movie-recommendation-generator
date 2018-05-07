/*
 * This class is a reader of files that contain user
 * rating information. It is called to open and retrieve
 * lines of text from a review file. It also checks to see if
 * the file changes and can be observable by classes that
 * might need to know if updates take place/
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RatingReader extends Reader {
	
	/*
	 * The constructor first runs the read function as directed
	 * by the abstract reader class then begins checking
	 * for updates.
	 */
	public RatingReader(String inputFileName) {
		super(inputFileName);
	}

	/*
	 * This class reads in a ratings file line by line
	 * and stores each line in an array list 
	 */
	public List<String> read() {
		File inputFile = new File(super.fileName);
		Scanner in;
		try {
			in = new Scanner(inputFile);
			while (in.hasNextLine()) {
				String test = in.nextLine();
				String regex = "\\d*::\\d*::\\d*::\\d*";
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

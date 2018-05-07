/**
 * This class represents a concrete factory
 * for returning reader objects. It takes in a filename
 * and checks the format of the file. Based on which format
 * the first line is written as, the factory returns
 * the appropriate reader that will splice the text.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReaderFactory {
	public Reader getIncomingList(String fileName) {
		File inputFile = new File(fileName);
		try {
			Scanner in = new Scanner(inputFile);
			String firstLine = in.nextLine();
			String regex1 = "\\d*::\\d*::\\d*::\\d*";
			String regex2 = "\\d*::.*::.*";
			Pattern pattern1 = Pattern.compile(regex1);
			Pattern pattern2 = Pattern.compile(regex2);
			Matcher matcher1 = pattern1.matcher(firstLine);
			Matcher matcher2 = pattern2.matcher(firstLine);
			if (matcher1.matches()) {
				return new RatingReader(fileName);
			} else if (matcher2.matches()) {
				return new MovieReader(fileName);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}

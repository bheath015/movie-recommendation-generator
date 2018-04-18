import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RatingReader extends Reader {
	
	public RatingReader(String inputFileName) {
		super(inputFileName);
	}

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
					super.output.add(in.nextLine());
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return super.output;
	}

}

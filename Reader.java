import java.util.ArrayList;
import java.util.List;

public abstract class Reader {
	String fileName;
	List<String> output;
	
	public Reader(String fileNameInput) {
		fileName = fileNameInput;
		output = new ArrayList<String>();
	}
	
	public abstract List<String> read();
}

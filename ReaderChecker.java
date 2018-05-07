import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReaderChecker implements InterObservable {

	private ArrayList<InterObserver> observers;
	
	public ReaderChecker() {
		observers = new ArrayList<InterObserver>();
	}
	
	/*
	 * This method checks to see if an update
	 * was made to the file it is responsible for reading.
	 * If a line was added, then it notifies observers.
	 */
	public int checkChanges(String fileName, int lastLineCount) {
		int lineCount = 0;
			if (lastLineCount != getLength(fileName)) {
				notifyObservers();
			}
			return lineCount;
	}
	
	/*
	 * Adds an observer to the observer list
	 */
	public void add(InterObserver o) {
		observers.add(o);
		
	}

	/*
	 * Removes an observer from the observer list
	 */
	public void remove(InterObserver o) {
		observers.remove(o);	
	}

	/*
	 * Function to iterate through the observer list and
	 * notify them that a change has taken place.
	 */
	public void notifyObservers() {
		for (InterObserver obs : observers) {
			obs.update();
		}
	}

	/**
	 * Returns the length of the file sent in as a parameter
	 * @param fileName is the file to check
	 * @return the number of lines
	 */
	public int getLength(String fileName) {
		Scanner in;
		File currentFile = new File(fileName);
		int lineCount = 0;
		try {
			in = new Scanner(currentFile);
			while (in.hasNextLine()) {
				in.nextLine();
				lineCount++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return lineCount;
	}
}

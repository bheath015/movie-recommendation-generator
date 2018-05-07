/**
 * This class represents a directory of all users
 * who have reviewed films. It observes reader files
 * for updates and can be observed by other objects
 * that need to know if additional information was added
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserDirectory {

	HashMap<Integer, User> users;
	String fileName;
	ArrayList<InterObserver> observers;
	
	/**
	 * Constructor begins the repeated check for updates
	 * to the data files
	 * @param ratingsFile is the filepath for the data file
	 */
	public UserDirectory(String ratingsFile) {
		users = new HashMap<Integer, User>();
		fileName = ratingsFile;
		observers = new ArrayList<InterObserver>();
		updateUsers();
	}
	
	/**
	 * Method that updates the map of users
	 * and storing information as user objects
	 * from the data file
	 */
	public void updateUsers() {
		users = new HashMap<Integer, User>();
		ReaderFactory rf = new ReaderFactory();
		Reader reader = rf.getIncomingList(fileName);
		List<String> allRatings = reader.read();
		for (String rating : allRatings) {
			String[] inLine = rating.split("::");
			int id = Integer.parseInt(inLine[0]);
			int filmID = Integer.parseInt(inLine[1]);
			double filmRating = Double.parseDouble(inLine[2]);
			if (users.containsKey(id)) {
				users.get(id).addFilm(filmID, filmRating);
			} else {
				User newUser = new User(id);
				newUser.addFilm(filmID, filmRating);
				users.put(id, newUser);
			}
		}
	}
	
	/**
	 * Getter for the user map
	 * @return the user map
	 */
	public HashMap<Integer, User> getUsers() {
		return users;
	}
}

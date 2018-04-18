import java.util.HashMap;
import java.util.List;

public class UserDirectory {

	HashMap<Integer, User> users;
	
	public UserDirectory(String ratingsFile) {
		users = new HashMap<Integer, User>();
		ReaderFactory rf = new ReaderFactory();
		Reader reader = rf.getIncomingList(ratingsFile);
		List<String> allRatings = reader.read();
		for (String rating : allRatings) {
			String[] inLine = rating.split("::");
			int id = Integer.parseInt(inLine[0]);
			int filmID = Integer.parseInt(inLine[1]);
			int filmRating = Integer.parseInt(inLine[2]);
			if (users.containsKey(id)) {
				users.get(id).addFilm(filmID, filmRating);
			} else {
				User newUser = new User(id);
				newUser.addFilm(filmID, filmRating);
			}
		}
	}
	
	public HashMap<Integer, User> getUsers() {
		return users;
	}
	
	
}

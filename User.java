import java.util.HashMap;

public class User {
	
	int userID;
	HashMap<Integer, Integer> movieRatings;
	public User(int id) {
		movieRatings = new HashMap<Integer, Integer>();
		userID = id;
	}
	
	public void addFilm(Integer movieID, int score) {
		movieRatings.put(movieID, score);
	}
	
	public double getAverageRating() {
		if (movieRatings.size() == 0) {
			return 0;
		}
		int ratingsSum = 0;
		for (Integer rating : movieRatings.values()) {
			ratingsSum += rating;
		}
		return ratingsSum / movieRatings.size();
	}
	
}

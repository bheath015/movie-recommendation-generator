/**
 * This class represents a user and their ratings
 * of movies
 */
import java.util.ArrayList;
import java.util.HashMap;

public class User {
	
	int userID;
	HashMap<Integer, Double> movieRatings;
	
	/**
	 * The constructor establishes a hashmap to store movie reviews
	 * @param id is the user's id
	 */
	public User(int id) {
		movieRatings = new HashMap<Integer, Double>();
		userID = id;
	}
	
	/**
	 * This method adds a review to the movie map
	 * @param movieID is the id of the movie reviewed
	 * @param filmRating is the user's rating of the movie
	 */
	public void addFilm(Integer movieID, double filmRating) {
		movieRatings.put(movieID, filmRating);
	}
	
	/**
	 * Method to calculate and return the average rating for a use
	 * @return the average rating from the map
	 */
	public double getAverageRating() {
		if (movieRatings.size() == 0) {
			return 0;
		}
		int ratingsSum = 0;
		for (Double rating : movieRatings.values()) {
			ratingsSum += rating;
		}
		return ratingsSum / movieRatings.size();
	}
	
	/**
	 * Getter for review of specific movie
	 * @param movieID is the id of the movie
	 * @return the rating of that move
	 */
	public double getRating(int movieID) {
		if (movieRatings.containsKey(movieID)){
			return movieRatings.get(movieID);
		}
		else {
			return 0;
		}
	}
	
	/**
	 * Getter for user's movies they reviewed
	 * @return a list of all reviewed movies
	 */
	public ArrayList<Integer> getAllMovies(){
		ArrayList<Integer> allMovies = new ArrayList<Integer>();
		
		for(Integer key : movieRatings.keySet()){
			allMovies.add(key);
		}
		return allMovies;
	}
	
}

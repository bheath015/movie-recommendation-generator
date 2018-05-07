import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/*
 * A class to assist with calculation of user similarities and
 * determining the neighborhood of users for a given movie.
 */
public class SimilarityCalculator {

	private HashMap<Integer, User> userMap;
	private HashMap<Integer, HashMap<Integer, Double>> allSimilarities;
	
	// public constructor
	public SimilarityCalculator(String userFile) {
		UserDirectory ud = new UserDirectory(userFile);
		userMap = ud.getUsers();
		allSimilarities = new HashMap<Integer, HashMap<Integer, Double>>();
	}
	
	// Calculates the pearson similarity score for a given user and every
	// other user in the database
	protected HashMap<Integer, Double> calculateAllSimilarities(User user) {
		if (allSimilarities.containsKey(user)) {
			return allSimilarities.get(user);
		}
		
		HashMap<Integer, Double> output = new HashMap<Integer, Double>();
		for (HashMap.Entry<Integer, User> otherUser : userMap.entrySet()) {
			double numerator = 0;
			double denominatorLeft = 0;
			double denominatorRight = 0;
			boolean allRatingsEqual = false;
			
			for (int movie : user.movieRatings.keySet()) {
				if (otherUser.getValue().movieRatings.containsKey(movie)) {
					double userValue = user.movieRatings.get(movie) - user.getAverageRating();
					double otherValue = otherUser.getValue().movieRatings.get(movie) - otherUser.getValue().getAverageRating();
					
					if (userValue == 0){
						allRatingsEqual = true;
					}
					
					numerator += userValue * otherValue;
					denominatorLeft += Math.pow(userValue, 2);
					denominatorRight += Math.pow(otherValue, 2);
				}			
				
			}
			
			if (allRatingsEqual){
				output.put(otherUser.getKey(), (numerator / (denominatorLeft * denominatorRight)));
			}
			
			else {
				
				if (denominatorRight == 0 || denominatorLeft == 0){
					output.put(otherUser.getKey(), 0.0);
				}
				
				else {	
					denominatorLeft = Math.sqrt(denominatorLeft);
					denominatorRight = Math.sqrt(denominatorRight);	
					output.put(otherUser.getKey(), (numerator / (denominatorLeft * denominatorRight)));
				}
								
			}
			
			allRatingsEqual = false;
			
		}
		
		return output;	
	}
	
	
	protected Set<Integer> getMovieSubset(int userID, int count){
		User user = userMap.get(userID);
		HashMap<Integer, Double> SimilarCorrelations = calculateAllSimilarities(user);
		TreeMap<Double, Integer> SimilarUsers = new TreeMap<Double, Integer>();
		
		for (Map.Entry<Integer, Double> entry : SimilarCorrelations.entrySet()){
			SimilarUsers.put(entry.getValue(), entry.getKey());
		}
		
		ArrayList<Integer> movieSubset = new ArrayList<Integer>();
		
		int threshold = SimilarUsers.size();
		if (count < threshold) {
			threshold = count;
		}
		
		for (int i=0; i < threshold; i++){
			double mostSimUser = SimilarUsers.lastKey();
			User otherUser = userMap.get(SimilarUsers.get(mostSimUser));
			ArrayList<Integer> otherUsersMovies = otherUser.getAllMovies();
			
			for (Integer movieID: otherUsersMovies){
				double otherUserAvg = otherUser.getAverageRating();
				double currentRating = otherUser.movieRatings.get(movieID);
				
				if (!user.getAllMovies().contains(movieID) &&  currentRating > otherUserAvg){
					movieSubset.add(movieID);
				}
			}		
			
			SimilarUsers.remove(mostSimUser);
		}
		
		Set<Integer> movies = new HashSet<>();
		movies.addAll(movieSubset);
		
		return movies;	
	}
	
	// get neighborhood of users closest to the provided user,
	// using a size threshold of at most 20
	protected HashMap<User, Double> getNeighborhood(int userID, int movieID){
		User user = userMap.get(userID);
		
		HashMap<Integer, Double> similarities = calculateAllSimilarities(user);
		HashMap<User, Double> neighborhood = new HashMap<User, Double>();
		
		for (Map.Entry<Integer, Double> entry : similarities.entrySet()){
			// get user id and similarity
			Integer key = entry.getKey();
			Double similarity = entry.getValue();
			
			// get user object from id and add to map
			User neighbor = userMap.get(key);
			
			// only add to neighborhood if user has rated the movie
			if (neighbor.movieRatings.containsKey(movieID)){
				neighborhood.put(neighbor, similarity);
				
				if (neighborhood.size() == 20){
					return neighborhood;
				}
			}
		}
		
		return neighborhood;
	}
	
	// Helper function to get average rating for a user 
	protected double getAvgRating(int userID){
		User user = userMap.get(userID);
		return user.getAverageRating();
	}
	
}

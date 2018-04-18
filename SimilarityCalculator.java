import java.util.HashMap;

public class SimilarityCalculator {

	private static HashMap<Integer, User> userMap;
	private static HashMap<Integer, HashMap<Integer, Double>> allSimilarities;
	
	public SimilarityCalculator(String userFile) {
		UserDirectory ud = new UserDirectory(userFile);
		userMap = ud.getUsers();
		allSimilarities = new HashMap<Integer, HashMap<Integer, Double>>();
	}
	
	private static HashMap<Integer, Double> calculateAllSimilarities(User user) {
		if (allSimilarities.containsKey(user)) {
			return allSimilarities.get(user);
		}
		HashMap<Integer, Double> output = new HashMap<Integer, Double>();
		for (HashMap.Entry<Integer, User> otherUser : userMap.entrySet()) {
			double numerator = 0;
			double denominatorLeft = 0;
			double denominatorRight = 0;
			for (int movie : user.movieRatings.keySet()) {
				if (otherUser.getValue().movieRatings.containsKey(movie)) {
					double userValue = user.movieRatings.get(movie) - user.getAverageRating();
					double otherValue = otherUser.getValue().movieRatings.get(movie) - otherUser.getValue().getAverageRating();
					numerator += userValue * otherValue;
					denominatorLeft += Math.pow(userValue, 2);
					denominatorRight += Math.pow(otherValue, 2);
				}
			}
			denominatorLeft = Math.sqrt(denominatorLeft);
			denominatorRight = Math.sqrt(denominatorRight);
			output.put(otherUser.getKey(), (numerator / (denominatorLeft * denominatorRight)));
		}
		return output;
		
	}
}

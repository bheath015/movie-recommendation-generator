import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Predictor {
		
	private MovieLibrary ml;
	private SimilarityCalculator sim;
	
	// public constructor
	public Predictor(String userfile, String moviefile){
		sim = new SimilarityCalculator(userfile);
		ml = new MovieLibrary(moviefile);
	}
	
	public double expectedRating(int userID, int movieID){
		HashMap<User, Double> neighborhood = sim.getNeighborhood(userID, movieID);
		double curUserAvg = sim.getAvgRating(userID);
		
		double numerator = 0;
		double denominator = 0;
		
		for (Map.Entry<User, Double> entry : neighborhood.entrySet()){	
			User user = entry.getKey();
			double similarity = entry.getValue();
			double currentMovieRating = user.getRating(movieID);
			double meanRating = user.getAverageRating();
			
			numerator += similarity * (currentMovieRating - meanRating);
			denominator += Math.abs(similarity);
		}
		
		// If the current user has rated all movies the same return 
		// their average rating as the expected rating for new movie.
		if (Double.isNaN(numerator) || Double.isNaN(denominator)){
			return curUserAvg;
		}
		
		if (denominator == 0){
			return 0.0;
		}
		
		double expectedRating = curUserAvg + (numerator/denominator);
		return expectedRating;	
	}

	
	public ArrayList<String> getTopMovies(int userID, int threshold){
		
		TreeMap<Double, String> topMovies = new TreeMap<Double, String>();
		Set<Integer> likelyMovies = sim.getMovieSubset(userID, threshold);
		
		System.out.println(likelyMovies);
		System.out.println(likelyMovies.size());
		
		for (Integer movieID : likelyMovies){

			Movie curMovie = ml.movies.get(movieID);
			
			double expectedR = expectedRating(userID, movieID);
			double min = 0;
			
			if (topMovies.size() != 0){
				min = topMovies.firstKey();
			}

			if (topMovies.size() < threshold){
				topMovies.put(expectedR,  curMovie.getTitle());	
			}
			
			else if (expectedR > min){
				topMovies.remove(min);
				topMovies.put(expectedR, curMovie.getTitle());
			}
		}
		
		ArrayList<String> topMovieTitles = new ArrayList<String>();
		for (Map.Entry<Double, String> entry : topMovies.entrySet()){
			topMovieTitles.add(entry.getValue());
		}
		
		return topMovieTitles;
	}

}

/*
 * This class represents a collection of movies.
 * It stores all movies, observes the movie file reader
 * to check for updates, and alerts observers if an update
 * has taken place.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MovieLibrary {

	HashMap<Integer, Movie> movies;
	String fileName;
	Reader reader;
	ArrayList<InterObserver> observers;
	
	/*
	 * constructor runs the first iteration of update map
	 */
	public MovieLibrary(String moviesFile) {
		fileName = moviesFile;
		observers = new ArrayList<InterObserver>();
		updateMap();
	}
	
	/*
	 * This method splites the lines incoming from the reader
	 * and completes movie objects which are then stored
	 * in the movie map.
	 */
	private void updateMap() {
		movies = new HashMap<Integer, Movie>(); 
		ReaderFactory rf = new ReaderFactory();
		reader = rf.getIncomingList(fileName);
		List<String> allMovies = reader.read();
		for (String movie : allMovies) {
			String regex = "(\\d*)::(.*)\\((\\d*)\\)::(.*)";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(movie);
			int id = 0;
			String title = null;
			String yearString = null;
			String allGenresTogether = null;
			if (matcher.matches()) {
				id = Integer.parseInt(matcher.group(1));
				title = matcher.group(2);
				yearString = matcher.group(3);
				allGenresTogether = matcher.group(4);
			}
			String[] allGenres = allGenresTogether.split("|");
			ArrayList<String> genreAL = new ArrayList<String>();
			for (int i = 0; i < allGenres.length; i++) {
				genreAL.add(allGenres[i]);
			}
			if (!movies.containsKey(id)) {
				Movie newMovie = new Movie(id, title, Integer.parseInt(yearString), genreAL);
				movies.put(id, newMovie);
			} 
		}
	}
}

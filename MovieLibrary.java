import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MovieLibrary {

	HashMap<Integer, Movie> movies;
	
	public MovieLibrary(String moviesFile) {
		movies = new HashMap<Integer, Movie>(); 
		ReaderFactory rf = new ReaderFactory();
		Reader reader = rf.getIncomingList(moviesFile);
		List<String> allMovies = reader.read();
		for (String movie : allMovies) {
			String[] inLine = movie.split("::");
			int id = Integer.parseInt(inLine[0]);
			String[] titleInfo = inLine[1].split(" ");
			String title = titleInfo[0];
			String yearString = titleInfo[1].substring(1, 5);
			String[] allGenres = inLine[2].split("|");
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

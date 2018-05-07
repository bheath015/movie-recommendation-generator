/*
 * This class represents a movie. It includes
 * all information that we can receive about a movie
 */
import java.util.ArrayList;

public class Movie {

	int movieID;
	String title;
	int year;
	ArrayList<String> genres;
	
	public Movie(int movieIDInput, String titleInput, int yearInput, ArrayList<String> genresInput) {
		movieID = movieIDInput;
		title = titleInput;
		year = yearInput;
		genres = genresInput;
	}
	
	/*
	 * Getter for movieID
	 */
	public int getMovieID() {
		return movieID;
	}
	
	/*
	 * Getter for title
	 */
	public String getTitle() { 
		return title;
	}
	
	/*
	 * Getter for movie year
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * Getter for genre
	 */
	public ArrayList<String> getGenres() {
		return genres;
	}
}

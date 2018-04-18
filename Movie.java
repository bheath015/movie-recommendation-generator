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
	
	public int getMovieID() {
		return movieID;
	}
	
	public String getTitle() { 
		return title;
	}
	
	public int getYear() {
		return year;
	}
	
	public ArrayList<String> getGenres() {
		return genres;
	}
}

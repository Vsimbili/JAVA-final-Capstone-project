import edu.duke.*;
import org.apache.commons.csv.*;
import java.util.*;

public class FirstRatings {

    // Load movies from CSV file
    public ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> movies = new ArrayList<>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();

        for (CSVRecord record : parser) {
            String id = record.get("id");
            String title = record.get("title");
            String year = record.get("year");
            String country = record.get("country");
            String genre = record.get("genre");
            String director = record.get("director");
            int minutes = Integer.parseInt(record.get("minutes"));
            String poster = record.get("poster");

            Movie m = new Movie(id, title, year, genre, director, country, poster, minutes);
            movies.add(m);
        }

        return movies;
    }

    // Load raters from CSV file
    public ArrayList<EfficientRater> loadRaters(String filename) {
        ArrayList<EfficientRater> raters = new ArrayList<>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();

        HashMap<String, EfficientRater> raterMap = new HashMap<>();

        for (CSVRecord record : parser) {
            String raterID = record.get("rater_id");
            String movieID = record.get("movie_id");
            double rating = Double.parseDouble(record.get("rating"));

            EfficientRater rater;
            if (raterMap.containsKey(raterID)) {
                rater = raterMap.get(raterID);
            } else {
                rater = new EfficientRater(raterID);
                raterMap.put(raterID, rater);
            }
            rater.addRating(movieID, rating);
        }

        for (EfficientRater r : raterMap.values()) {
            raters.add(r);
        }

        return raters;
    }
}

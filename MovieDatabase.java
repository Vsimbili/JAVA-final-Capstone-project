import edu.duke.*;
import org.apache.commons.csv.*;
import java.util.*;

public class MovieDatabase {
    private static HashMap<String, Movie> ourMovies;

    private static void initialize() {
        if (ourMovies == null) {
            ourMovies = new HashMap<>();
        }
    }

    public static void initialize(String moviefile) {
        initialize();
        loadMovies("data/" + moviefile);
    }

    private static void loadMovies(String filename) {
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
            ourMovies.put(id, m);
        }
    }

    public static boolean containsID(String id) {
        return ourMovies.containsKey(id);
    }

    public static Movie getMovie(String id) {
        return ourMovies.get(id);
    }

    public static String getTitle(String id) {
        return getMovie(id).getTitle();
    }

    public static String getGenres(String id) {
        return getMovie(id).getGenres();
    }

    public static int getMinutes(String id) {
        return getMovie(id).getMinutes();
    }

    public static String getPoster(String id) {
        return getMovie(id).getPoster();
    }

    public static String getCountry(String id) {
        return getMovie(id).getCountry();
    }

    public static String getDirector(String id) {
        return getMovie(id).getDirector();
    }

    public static String getYear(String id) {
        return getMovie(id).getYear();
    }

    public static int size() {
        return ourMovies.size();
    }

    public static ArrayList<String> filterBy(Filter f) {
        ArrayList<String> list = new ArrayList<>();
        for (String id : ourMovies.keySet()) {
            if (f.satisfies(id)) {
                list.add(id);
            }
        }
        return list;
    }
}

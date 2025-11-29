import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class RaterDatabase {
    private static HashMap<String,Rater> ourRaters;

    private static void initialize() {
        if (ourRaters == null) {
            ourRaters = new HashMap<String,Rater>();
        }
    }

    public static void initialize(String filename) {
        if (ourRaters == null) {
            ourRaters = new HashMap<String,Rater>();
            addRatings("data/" + filename);
        }
    }

    public static void addRatings(String filename) {
        initialize();
        FileResource fr = new FileResource(filename);
        CSVParser csvp = fr.getCSVParser();

        for (CSVRecord rec : csvp) {
            String raterID = rec.get("rater_id");
            String movieID = rec.get("movie_id");
            double rating = Double.parseDouble(rec.get("rating"));
            addRaterRating(raterID, movieID, rating);
        }
    }

    public static void addRaterRating(String raterID, String movieID, double rating) {
        initialize();
        Rater rater = null;

        if (ourRaters.containsKey(raterID)) {
            rater = ourRaters.get(raterID);
        } else {
            rater = new EfficientRater(raterID);
            ourRaters.put(raterID, rater);
        }
        rater.addRating(movieID, rating);
    }

    public static Rater getRater(String id) {
        initialize();
        return ourRaters.get(id);
    }

    public static ArrayList<Rater> getRaters() {
        initialize();
        return new ArrayList<Rater>(ourRaters.values());
    }

    public static int size() {
        return ourRaters.size();
    }
}

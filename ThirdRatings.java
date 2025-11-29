import java.util.*;

public class ThirdRatings {

    public ThirdRatings() {
        // empty constructor
        RaterDatabase.initialize("ratings.csv"); // optionally load ratings here
    }

    // Compute average rating for a movie ID if it has at least minimalRaters ratings
    private double getAverageByID(String movieID, int minimalRaters) {
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        double total = 0.0;
        int count = 0;

        for (Rater r : raters) {
            if (r.hasRating(movieID)) {
                total += r.getRating(movieID);
                count++;
            }
        }

        if (count >= minimalRaters) {
            return total / count;
        }

        return 0.0;
    }

    // Get list of average ratings for all movies with at least minimalRaters
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> avgRatings = new ArrayList<>();
        ArrayList<String> movieIDs = MovieDatabase.filterBy(new TrueFilter());

        for (String id : movieIDs) {
            double avg = getAverageByID(id, minimalRaters);
            if (avg > 0.0) {
                avgRatings.add(new Rating(id, avg));
            }
        }

        return avgRatings;
    }

    // Get list of average ratings for movies that satisfy a filter
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> avgRatings = new ArrayList<>();
        ArrayList<String> movieIDs = MovieDatabase.filterBy(filterCriteria);

        for (String id : movieIDs) {
            double avg = getAverageByID(id, minimalRaters);
            if (avg > 0.0) {
                avgRatings.add(new Rating(id, avg));
            }
        }

        return avgRatings;
    }
}


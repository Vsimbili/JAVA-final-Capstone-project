import java.util.*;

public class FourthRatings {

    public FourthRatings() {
        // empty constructor
        RaterDatabase.initialize("ratings.csv"); // load raters
    }

    // ----------------- Helper method -----------------

    // Translate 0–10 scale to -5 to 5 and compute dot product between two raters
    private double dotProduct(Rater me, Rater r) {
    double sum = 0.0;
    for (String movieID : me.getItemsRated()) {
        if (r.hasRating(movieID)) {
            double meScore = me.getRating(movieID) - 5;
            double rScore = r.getRating(movieID) - 5;
            sum += meScore * rScore;
        }
    }
    return sum;
}


    // ----------------- Similarity -----------------

    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> list = new ArrayList<>();
        Rater me = RaterDatabase.getRater(id);

        for (Rater r : RaterDatabase.getRaters()) {
            if (!r.getID().equals(id)) {
                double score = dotProduct(me, r);
                if (score > 0.0) {
                    list.add(new Rating(r.getID(), score));
                }
            }
        }

        Collections.sort(list, Collections.reverseOrder());
        return list;
    }

    // ----------------- Weighted Ratings -----------------

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> weightedRatings = new ArrayList<>();
        ArrayList<Rating> similarities = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());

        for (String movieID : movies) {
            double total = 0.0;
            double sumSim = 0.0;
            int count = 0;

            for (int k = 0; k < Math.min(numSimilarRaters, similarities.size()); k++) {
                Rating simRating = similarities.get(k);
                Rater r = RaterDatabase.getRater(simRating.getItem());
                if (r.hasRating(movieID)) {
                    total += simRating.getValue() * r.getRating(movieID);
                    sumSim += simRating.getValue();
                    count++;
                }
            }

            if (count >= minimalRaters && sumSim != 0.0) {
                weightedRatings.add(new Rating(movieID, total / sumSim));
            }
        }

        Collections.sort(weightedRatings, Collections.reverseOrder());
        return weightedRatings;
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters,
                                                       int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> weightedRatings = new ArrayList<>();
        ArrayList<Rating> similarities = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);

        for (String movieID : movies) {
            double total = 0.0;
            double sumSim = 0.0;
            int count = 0;

            for (int k = 0; k < Math.min(numSimilarRaters, similarities.size()); k++) {
                Rating simRating = similarities.get(k);
                Rater r = RaterDatabase.getRater(simRating.getItem());
                if (r.hasRating(movieID)) {
                    total += simRating.getValue() * r.getRating(movieID);
                    sumSim += simRating.getValue();
                    count++;
                }
            }

            if (count >= minimalRaters && sumSim != 0.0) {
                weightedRatings.add(new Rating(movieID, total / sumSim));
            }
        }

        Collections.sort(weightedRatings, Collections.reverseOrder());
        return weightedRatings;
    }

    // Optional: you can also copy getAverageByID, getAverageRatings, getAverageRatingsByFilter
    // from ThirdRatings if you want basic averaging methods.
}

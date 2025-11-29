import java.util.*;

public class MovieRunnerWithFilters {

    public void printAverageRatings() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        FourthRatings fr = new FourthRatings();

        int minimalRaters = 35;
        ArrayList<Rating> avgRatings = fr.getSimilarRatingsByFilter("65", 10, minimalRaters, new TrueFilter());

        System.out.println("Found " + avgRatings.size() + " movies with at least " + minimalRaters + " ratings.");
        for (Rating r : avgRatings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
        }
    }

    public void printAverageRatingsByYearAfterAndGenre() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        FourthRatings fr = new FourthRatings();

        int minimalRaters = 8;
        int year = 1990;
        String genre = "Drama";

        AllFilters af = new AllFilters();
        af.addFilter(new YearAfterFilter(year));
        af.addFilter(new GenreFilter(genre));

        ArrayList<Rating> avgRatings = fr.getSimilarRatingsByFilter("65", 10, minimalRaters, af);

        System.out.println("Movies after " + year + " in genre " + genre + ":");
        for (Rating r : avgRatings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()) + " (" + MovieDatabase.getYear(r.getItem()) + ")");
        }
    }

    public void printAverageRatingsByGenre() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        FourthRatings fr = new FourthRatings();

        int minimalRaters = 20;
        String genre = "Comedy";

        ArrayList<Rating> avgRatings = fr.getSimilarRatingsByFilter("65", 10, minimalRaters, new GenreFilter(genre));

        System.out.println("Movies in genre: " + genre);
        for (Rating r : avgRatings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
        }
    }

    public void printAverageRatingsByMinutes() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        FourthRatings fr = new FourthRatings();

        int minimalRaters = 5;
        int minMinutes = 90;
        int maxMinutes = 180;

        ArrayList<Rating> avgRatings = fr.getSimilarRatingsByFilter("65", 10, minimalRaters,
                new MinutesFilter(minMinutes, maxMinutes));

        System.out.println("Movies with minutes between " + minMinutes + " and " + maxMinutes + ":");
        for (Rating r : avgRatings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()) +
                    " (" + MovieDatabase.getMinutes(r.getItem()) + " min)");
        }
    }
}

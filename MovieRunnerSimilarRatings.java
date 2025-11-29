import java.util.*;

public class MovieRunnerSimilarRatings {

    public void printSimilarRatings() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        FourthRatings fr = new FourthRatings();

        String raterID = "65";
        int numSimilarRaters = 20;
        int minimalRaters = 5;

        ArrayList<Rating> ratings = fr.getSimilarRatings(raterID, numSimilarRaters, minimalRaters);

        System.out.println("Top recommended movies for rater " + raterID + ":");
        for (Rating r : ratings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
        }
    }

    public void printSimilarRatingsByGenre() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        FourthRatings fr = new FourthRatings();

        String raterID = "65";
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        String genre = "Action";

        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, new GenreFilter(genre));

        System.out.println("Top recommended " + genre + " movies for rater " + raterID + ":");
        for (Rating r : ratings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()) + " | Genres: " + MovieDatabase.getGenres(r.getItem()));
        }
    }

    public void printSimilarRatingsByDirector() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        FourthRatings fr = new FourthRatings();

        String raterID = "1034";
        int numSimilarRaters = 10;
        int minimalRaters = 3;
        String directors = "Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone";

        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, new DirectorFilter(directors));

        System.out.println("Top recommended movies by directors " + directors + " for rater " + raterID + ":");
        for (Rating r : ratings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()) + " | Directors: " + MovieDatabase.getDirector(r.getItem()));
        }
    }

    public void printSimilarRatingsByGenreAndMinutes() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        FourthRatings fr = new FourthRatings();

        String raterID = "65";
        int numSimilarRaters = 10;
        int minimalRaters = 5;
        String genre = "Adventure";
        int minMinutes = 100;
        int maxMinutes = 200;

        AllFilters af = new AllFilters();
        af.addFilter(new GenreFilter(genre));
        af.addFilter(new MinutesFilter(minMinutes, maxMinutes));

        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, af);

        System.out.println("Top recommended " + genre + " movies with minutes " + minMinutes + "-" + maxMinutes + " for rater " + raterID + ":");
        for (Rating r : ratings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()) +
                    " | Minutes: " + MovieDatabase.getMinutes(r.getItem()) +
                    " | Genres: " + MovieDatabase.getGenres(r.getItem()));
        }
    }

    public void printSimilarRatingsByYearAfterAndMinutes() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        FourthRatings fr = new FourthRatings();

        String raterID = "65";
        int numSimilarRaters = 10;
        int minimalRaters = 5;
        int yearAfter = 2000;
        int minMinutes = 80;
        int maxMinutes = 100;

        AllFilters af = new AllFilters();
        af.addFilter(new YearAfterFilter(yearAfter));
        af.addFilter(new MinutesFilter(minMinutes, maxMinutes));

        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, af);

        System.out.println("Top recommended movies after " + yearAfter + " with minutes " + minMinutes + "-" + maxMinutes + " for rater " + raterID + ":");
        for (Rating r : ratings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()) +
                    " | Year: " + MovieDatabase.getYear(r.getItem()) +
                    " | Minutes: " + MovieDatabase.getMinutes(r.getItem()));
        }
    }
    public void printSimilarRatingsCustom(String raterID, int numSimilarRaters, int minimalRaters) {
    // Initialize the databases
    MovieDatabase.initialize("data/ratedmoviesfull.csv");
    RaterDatabase.initialize("data/ratings.csv");

    // Create FourthRatings object
    FourthRatings fr = new FourthRatings();

    // Get recommended movies
    ArrayList<Rating> ratings = fr.getSimilarRatings(raterID, numSimilarRaters, minimalRaters);

    System.out.println("Top recommended movies for rater " + raterID + ":");
    for (Rating r : ratings) {
        System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
    }
}

public void printSimilarRatingsByYearAfterAndMinutes314() {
    // Initialize databases
    MovieDatabase.initialize("ratedmoviesfull.csv");
    RaterDatabase.initialize("ratings.csv");

    // Create FourthRatings object
    FourthRatings fr = new FourthRatings();

    // Create filters: YearAfter + Minutes
    YearAfterFilter yearFilter = new YearAfterFilter(1975);
    MinutesFilter minutesFilter = new MinutesFilter(70, 200);
    AllFilters allFilters = new AllFilters();
    allFilters.addFilter(yearFilter);
    allFilters.addFilter(minutesFilter);

    // Get recommended movies using the combined filter
    ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter("314", 10, 5, allFilters);

    // Print the results
    System.out.println("Top recommended movies for rater 314, year after 1975, 70-200 min:");
    for (Rating r : ratings) {
        System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem())
                           + " (" + MovieDatabase.getYear(r.getItem()) + ", "
                           + MovieDatabase.getMinutes(r.getItem()) + " min)");
    }
}



}

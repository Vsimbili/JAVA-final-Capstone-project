
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author Venkata Sai Simbili
 * @version 1.5.7
 */
import java.util.*;
    
public class RecommendationRunner implements Recommender {

    // Return a list of movies for the user to rate
    
    public ArrayList<String> getItemsToRate() {
        ArrayList<String> movies = new ArrayList<String>();

        // Load the full movie database
        MovieDatabase.initialize("ratedmoviesfull.csv");

        // Select about 15–20 movies (not too many)
        // Here we choose recent movies + popular ones
        for (String id : MovieDatabase.filterBy(new YearAfterFilter(2010))) {
            if (movies.size() < 20) {
                movies.add(id);
            } else break;
        }

        // If fewer than 20 movies, add more random movies to reach 20
        ArrayList<String> allMovies = MovieDatabase.filterBy(new TrueFilter());
        Collections.shuffle(allMovies);

        while (movies.size() < 20) {
            String id = allMovies.get(movies.size());
            if (!movies.contains(id)) movies.add(id);
        }

        return movies;
    }

    // Print out recommended movies in an HTML table
    public void printRecommendationsFor(String webRaterID) {
        // Load the databases
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");

        FourthRatings fr = new FourthRatings();

        // Generate recommendations based on similarity
        ArrayList<Rating> recs = fr.getSimilarRatings(webRaterID, 20, 5);

        // If no recommendations
        if (recs.size() == 0) {
            System.out.println("<h2>No movie recommendations found.</h2>");
            return;
        }

        // Limit to 10–15 recommendations
        int limit = Math.min(15, recs.size());

        // --- HTML Output ---
        System.out.println("<style>");
        System.out.println("table {font-family: Arial; border-collapse: collapse; width: 80%; margin: 20px auto;}");
        System.out.println("th, td {border: 1px solid #dddddd; text-align: left; padding: 8px;}");
        System.out.println("th {background-color: #4CAF50; color: white;}");
        System.out.println("tr:nth-child(even) {background-color: #f2f2f2;}");
        System.out.println("</style>");

        System.out.println("<h2 style='text-align:center;'>Your Movie Recommendations</h2>");

        System.out.println("<table>");
        System.out.println("<tr><th>Title</th><th>Year</th><th>Genre</th><th>Minutes</th><th>Rating</th></tr>");

        for (int i = 0; i < limit; i++) {
            Rating r = recs.get(i);
            String id = r.getItem();

            System.out.println("<tr>");
            System.out.println("<td>" + MovieDatabase.getTitle(id) + "</td>");
            System.out.println("<td>" + MovieDatabase.getYear(id) + "</td>");
            System.out.println("<td>" + MovieDatabase.getGenres(id) + "</td>");
            System.out.println("<td>" + MovieDatabase.getMinutes(id) + "</td>");
            System.out.println("<td>" + String.format("%.2f", r.getValue()) + "</td>");
            System.out.println("</tr>");
        }

        System.out.println("</table>");
    }
}

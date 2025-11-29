
import java.util.*;

public interface Recommender {
    // Returns a list of movie IDs to be rated by the user
    public ArrayList<String> getItemsToRate();

    // Prints out HTML displaying recommended movies for the user
    public void printRecommendationsFor(String webRaterID);
}

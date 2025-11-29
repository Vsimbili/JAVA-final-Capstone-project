import java.util.*;

public class EfficientRater implements Rater {
    private String myID;
    private HashMap<String, Double> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<>();
    }

    @Override
    public void addRating(String item, double rating) {
        myRatings.put(item, rating);
    }

    @Override
    public boolean hasRating(String item) {
        return myRatings.containsKey(item);
    }

    @Override
    public String getID() {
        return myID;
    }

    @Override
    public double getRating(String item) {
        if (myRatings.containsKey(item)) {
            return myRatings.get(item);
        }
        return -1.0;
    }

    @Override
    public int numRatings() {
        return myRatings.size();
    }

    @Override
    public ArrayList<String> getItemsRated() {
        return new ArrayList<>(myRatings.keySet());
    }
}

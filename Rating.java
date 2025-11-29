public class Rating implements Comparable<Rating> {
    private String item;
    private double value;

    public Rating(String item, double value) {
        this.item = item;
        this.value = value;
    }

    public String getItem() {
        return item;
    }

    public double getValue() {
        return value;
    }

    @Override
    public int compareTo(Rating other) {
        return Double.compare(this.value, other.value);
    }

    @Override
    public String toString() {
        return "[" + item + ", " + value + "]";
    }
}

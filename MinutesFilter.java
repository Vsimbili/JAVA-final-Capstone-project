public class MinutesFilter implements Filter {
    private int min;
    private int max;

    public MinutesFilter(int minMinutes, int maxMinutes) {
        min = minMinutes;
        max = maxMinutes;
    }

    @Override
    public boolean satisfies(String id) {
        int minutes = MovieDatabase.getMinutes(id);
        return minutes >= min && minutes <= max;
    }
}

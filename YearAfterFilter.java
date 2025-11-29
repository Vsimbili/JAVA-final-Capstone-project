public class YearAfterFilter implements Filter {
    private int year;

    public YearAfterFilter(int year) {
        this.year = year;
    }

    @Override
    public boolean satisfies(String id) {
        return Integer.parseInt(MovieDatabase.getYear(id)) >= year;
    }
}

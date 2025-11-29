public class Movie {
    private String id;
    private String title;
    private String year;
    private String country;
    private String genre;
    private String director;
    private int minutes;
    private String poster;

    public Movie(String anID, String aTitle, String aYear, String theGenre,
                 String aDirector, String aCountry, String aPoster, int theMinutes) {
        id = anID;
        title = aTitle;
        year = aYear;
        genre = theGenre;
        director = aDirector;
        country = aCountry;
        poster = aPoster;
        minutes = theMinutes;
    }

    public String getID() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getGenres() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public String getCountry() {
        return country;
    }

    public String getPoster() {
        return poster;
    }

    public int getMinutes() {
        return minutes;
    }

    @Override
    public String toString() {
        return "Movie[" + title + ", " + genre + ", " + year + "]";
    }
}

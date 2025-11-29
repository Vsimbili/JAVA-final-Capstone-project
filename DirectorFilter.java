public class DirectorFilter implements Filter {
    private String[] directors;

    public DirectorFilter(String directorList) {
        directors = directorList.split(",");
    }

    @Override
    public boolean satisfies(String id) {
        String movieDirectors = MovieDatabase.getDirector(id);
        for (String d : directors) {
            if (movieDirectors.contains(d.trim())) {
                return true;
            }
        }
        return false;
    }
}

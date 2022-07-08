package katas;

import model.BoxArt;
import model.Movie;
import util.DataUtil;
import java.util.Comparator;
import java.util.List;

/*
    Goal: Retrieve the url of the largest boxart using map() and reduce()
    DataSource: DataUtil.getMovieLists()
    Output: String
*/
public class Kata6 {
    private Kata6() {

    }
    public static String execute() {
        List<Movie> movies = DataUtil.getMovies();

        return movies.stream()
                .map(Movie::getBoxarts)
                .reduce((boxArts, another) -> {
            var boxArt1 = boxArts.stream()
                    .max(Comparator.comparing(BoxArt::getWidth))
                    .orElseThrow();
            var boxArt2 = another.stream()
                    .max(Comparator.comparing(BoxArt::getWidth))
                    .orElseThrow();
            var max = boxArt1.getWidth() > boxArt2.getWidth() ? boxArt1 : boxArt2;
            return List.of(max);

        }).orElseThrow().stream().map(BoxArt::getUrl).findFirst().orElseThrow();


    }
}

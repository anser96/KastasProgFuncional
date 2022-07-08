package katas;


import model.Movie;
import model.MovieList;
import util.DataUtil;
import java.util.*;
import java.util.stream.Stream;

/*
    Goal: Retrieve each video's id, title, middle interesting moment time, and smallest box art url
    DataSource: DataUtil.getMovies()
    Output: List of ImmutableMap.of("id", 5, "title", "some title", "time", new Date(), "url", "someUrl")
*/
public class Kata9 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        List<Map> list = new ArrayList<>();
        for (MovieList movieList : movieLists) {
            for (Movie movie : movieList.getVideos()) {
                Map<String, Object> id = Map.of(
                        "id", movie.getId(), "title", movie.getTitle(),
                        "time", movie.getInterestingMoments().stream()
                                .flatMap(interestingMoment -> Stream.of(interestingMoment.getTime()))
                                .mapToLong(Date::getTime)
                                .average()
                                .stream().mapToObj(prom -> new Date((long) prom)),
                        "url", movie.getBoxarts()
                                .stream()
                                .min(Comparator.comparing(boxArt -> boxArt.getUrl().length()))
                                .orElseThrow().getUrl()
                );
                list.add(id);
            }
        }
        return list;

    }
}

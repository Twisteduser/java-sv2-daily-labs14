package day02;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MovieService {
    private List<Movie> movies = new ArrayList<>();

    public void addMovie(Movie movie){
        movies.add(movie);
    }

    public  List<Movie> findMoviesContainsActor(String name){
        return movies.stream()
                .filter(m->m.getActors().contains(name))
                .collect(Collectors.toList());
    }

    public long findLengthOfLongestMovie(){
        return movies.stream()
                .mapToInt(m->m.getLength())
                .max().orElseThrow(()->new IllegalStateException("List is empty"));
    }

}

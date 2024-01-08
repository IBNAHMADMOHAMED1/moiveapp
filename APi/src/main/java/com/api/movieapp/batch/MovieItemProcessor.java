package com.api.movieapp.batch;

import com.api.movieapp.dto.MovieDto;
import com.api.movieapp.model.Movie;
import org.springframework.batch.item.ItemProcessor;

public class MovieItemProcessor implements ItemProcessor<Movie, Movie> {

    @Override
    public Movie process(Movie movie) throws Exception {
        // we have tow value of runtimeMinutes int or \N so we need to check if it is int or not
        // if it is not int we will set it to 0
        String runtimeMinutes = movie.getRuntimeMinutes();
        if (runtimeMinutes.equals("\\N")) {
            movie.setRuntimeMinutes("0");
        }
        return movie;



    }

}

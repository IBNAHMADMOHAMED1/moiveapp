package com.api.movieapp.service;

import com.api.movieapp.dto.MovieDto;
import com.api.movieapp.model.Movie;
import com.api.movieapp.repository.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Page<MovieDto> getFilteredAndSortedMovies(String primaryTitle, String originalTitle, String startYear, PageRequest pageRequest) {
        if (primaryTitle != null && originalTitle != null && startYear != null) {
            return performFilteredQuery(primaryTitle, originalTitle, startYear, pageRequest);
        } else if (primaryTitle != null) {
            return performFilteredByPrimaryTitle(primaryTitle, pageRequest);
        } else if (originalTitle != null) {
            return performFilteredByOriginalTitle(originalTitle, pageRequest);
        } else if (startYear != null) {
            return performFilteredByStartYear(startYear, pageRequest);
        }
        else {
            return performDefaultQuery(pageRequest);
        }
    }

    private Page<MovieDto> performFilteredQuery(String primaryTitle, String originalTitle, String startYear, PageRequest pageRequest) {
        Page<Movie> movies = movieRepository.findAllByPrimaryTitleContainingAndOriginalTitleContainingAndStartYear(primaryTitle, originalTitle, startYear, pageRequest);
        return movies.map(this::convertToDto);
    }

    private Page<MovieDto> performFilteredByPrimaryTitle(String primaryTitle, PageRequest pageRequest) {
        Page<Movie> movies = movieRepository.findAllByPrimaryTitleContaining(primaryTitle, pageRequest);
        return movies.map(this::convertToDto);
    }

    private Page<MovieDto> performFilteredByOriginalTitle(String originalTitle, PageRequest pageRequest) {
        Page<Movie> movies = movieRepository.findAllByOriginalTitleContaining(originalTitle, pageRequest);
        return movies.map(this::convertToDto);
    }

    private Page<MovieDto> performFilteredByStartYear(String startYear, PageRequest pageRequest) {
        Page<Movie> movies = movieRepository.findAllByStartYear(startYear, pageRequest);
        return movies.map(this::convertToDto);
    }

    private Page<MovieDto> performDefaultQuery(PageRequest pageRequest) {
        Page<Movie> movies = movieRepository.findAll(pageRequest);
        return movies.map(this::convertToDto);
    }


    private MovieDto convertToDto(Movie movie) {
        return MovieDto.builder()
                .tconst(movie.getTconst())
                .titleType(movie.getTitleType())
                .primaryTitle(movie.getPrimaryTitle())
                .originalTitle(movie.getOriginalTitle())
                .isAdult(movie.isAdult())
                .startYear(movie.getStartYear())
                .endYear(movie.getEndYear())
                .runtimeMinutes(movie.getRuntimeMinutes())
                .genres(movie.getGenres())
                .build();
    }
}

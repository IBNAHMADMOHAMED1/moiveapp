package com.api.movieapp.controller;

import com.api.movieapp.dto.MovieDto;
import com.api.movieapp.exception.MovieNotFoundException;
import com.api.movieapp.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<Page<MovieDto>> getAllMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String primaryTitle,
            @RequestParam(required = false) String originalTitle,
            @RequestParam(required = false) String startYear,
            @RequestParam(defaultValue = "desc") String ortBystartYear
    ) {
        Page<MovieDto> movies = movieService.getFilteredAndSortedMovies(
                primaryTitle, originalTitle, startYear,
                PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(ortBystartYear), "startYear"))
        );
        return ResponseEntity.ok(movies);
    }

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<Void> handleMovieNotFoundException(MovieNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred: " + e.getMessage());
    }

}

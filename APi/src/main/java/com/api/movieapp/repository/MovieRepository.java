package com.api.movieapp.repository;

import com.api.movieapp.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
    Page<Movie> findAllByPrimaryTitleContainingAndOriginalTitleContainingAndStartYear(String primaryTitle, String originalTitle, String startYear, Pageable pageable);

    Page<Movie> findAllByPrimaryTitleContainingOrOriginalTitleContainingOrStartYear(String primaryTitle, String originalTitle, String startYear, PageRequest of);

    Page<Movie> findAllByOriginalTitleContaining(String originalTitle, PageRequest of);

    Page<Movie> findAllByPrimaryTitleContaining(String primaryTitle, PageRequest pageRequest);

    Page<Movie> findAllByStartYear(String startYear, PageRequest pageRequest);
}

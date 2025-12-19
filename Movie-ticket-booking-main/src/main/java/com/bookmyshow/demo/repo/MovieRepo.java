package com.bookmyshow.demo.repo;

import com.bookmyshow.demo.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface MovieRepo extends JpaRepository<Movie,Long> {

    Optional<List<Movie>> findByGenre(String genre);

    Optional<Movie> findByTitle(String title);

    Optional<List<Movie>> findByLanguage(String lang);
}

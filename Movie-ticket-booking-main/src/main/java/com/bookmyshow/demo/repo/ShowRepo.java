package com.bookmyshow.demo.repo;

import com.bookmyshow.demo.entities.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShowRepo extends JpaRepository<Show,Long> {
//    Optional<List<Show>> findByMovie(String movie);
//
//    Optional<List<Show>> findByTheatre(String theatre);

    Optional<Show> findByMovieId(Long movieId);

    Optional<List<Show>> findByTheatreId(Long theatreId);
}

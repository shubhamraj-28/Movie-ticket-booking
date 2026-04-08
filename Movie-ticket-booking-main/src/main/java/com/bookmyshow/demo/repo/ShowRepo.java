package com.bookmyshow.demo.repo;

import com.bookmyshow.demo.entities.Show;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ShowRepo extends JpaRepository<Show,Long> {
//    Optional<List<Show>> findByMovie(String movie);
//
//    Optional<List<Show>> findByTheatre(String theatre);

    Optional<Show> findByMovieId(Long movieId);

    Optional<List<Show>> findByTheatreId(Long theatreId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM Show s WHERE s.id = :id")
    Optional<Show> findByIdWithPessimisticLock(@Param("id") Long id);
}

package com.bookmyshow.demo.repo;

import com.bookmyshow.demo.entities.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface TheatreRepo extends JpaRepository<Theatre,Long> {

    Optional<List<Theatre>> findByLocation(String location);


}

package com.bookmyshow.demo.service;

import com.bookmyshow.demo.dtos.MovieDTO;
import com.bookmyshow.demo.entities.Movie;
import com.bookmyshow.demo.repo.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepo repo;

    public List<Movie> getAllMovies(){
        return repo.findAll();
    }

    public Movie getMovieById(Long id){
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie with ID " + id + " not found"));
    }

    public Movie addMovie(MovieDTO movieDTO) {
        Movie movie = new Movie();

        movie.setTitle(movieDTO.getTitle());
        movie.setDescription(movieDTO.getDescription());
        movie.setGenre(movieDTO.getGenre());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setDuration(movieDTO.getDuration());
        movie.setLanguage(movieDTO.getLanguage());

        return repo.save(movie);
    }

    public List<Movie> getMoviesByGenre(String genre) {
        Optional<List<Movie>> movies = (repo.findByGenre(genre));

        if(movies.isEmpty()) throw new RuntimeException("Movies not found");
        return movies.get();
    }

    public Movie getMovieByTitle(String title) {
        return repo.findByTitle(title)
                .orElseThrow(() -> new RuntimeException("Movie " + title + " not found"));
    }

    public List<Movie> getMoviesByLanguage(String lang) {
        Optional<List<Movie>> movies = (repo.findByLanguage(lang));

        if(movies.isEmpty()) throw new RuntimeException("Movies not found");
        return movies.get();
    }

    public Movie updateMovie(Long id, MovieDTO movieDTO) {
        Movie movie = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie with ID " + id + " not found"));

        movie.setTitle(movieDTO.getTitle());
        movie.setDescription(movieDTO.getDescription());
        movie.setGenre(movieDTO.getGenre());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setDuration(movieDTO.getDuration());
        movie.setLanguage(movieDTO.getLanguage());

        return repo.save(movie);
    }

    public void deleteMovie(Long id) {
        Movie movie = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        repo.delete(movie);
    }
}

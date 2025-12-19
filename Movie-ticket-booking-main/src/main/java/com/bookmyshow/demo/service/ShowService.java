package com.bookmyshow.demo.service;

import com.bookmyshow.demo.dtos.ShowDTO;
import com.bookmyshow.demo.entities.Booking;
import com.bookmyshow.demo.entities.Movie;
import com.bookmyshow.demo.entities.Show;
import com.bookmyshow.demo.entities.Theatre;
import com.bookmyshow.demo.repo.MovieRepo;
import com.bookmyshow.demo.repo.ShowRepo;
import com.bookmyshow.demo.repo.TheatreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    @Autowired
    private ShowRepo showRepo;

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private TheatreRepo theatreRepo;

    public Show addShow(ShowDTO showDTO) {
        Movie movie = movieRepo.findById(showDTO.getMovieId())
                .orElseThrow(()->new RuntimeException("No movie found"));

        Theatre theatre = theatreRepo.findById(showDTO.getTheatreId())
                .orElseThrow(()->new RuntimeException("No Theatre found"));

        Show show = new Show();

        show.setShowtime(showDTO.getShowtime());
        show.setPrice(showDTO.getPrice());
        show.setMovie(movie);
        show.setTheatre(theatre);

        return showRepo.save(show);
    }

    public void deleteMovie(Long id) {
        if(!showRepo.existsById(id)){
            throw new RuntimeException("No Show with this id");
        }

        List<Booking> bookings = showRepo.findById(id).get().getBookings();

        if(!bookings.isEmpty()) throw new RuntimeException("Show with existing bookings can't be deleted");
        showRepo.deleteById(id);
    }

    public List<Show> getShowByMovie(Long movieId) {
        Optional<Show> shows = showRepo.findByMovieId(movieId);

        if(shows.isPresent()) return Collections.singletonList(shows.get());
        throw new RuntimeException("Shows not found");
    }

    public List<Show> getShowByTheatre(Long theatreId) {
        Optional<List<Show>> shows = showRepo.findByTheatreId(theatreId);

        if(shows.isEmpty()) throw  new RuntimeException("Shows not found");
        return shows.get();
    }

    public Show updateShow(Long id, ShowDTO showDTO) {
        Show show = showRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("No shows by this id"));

        Movie movie = movieRepo.findById(showDTO.getMovieId())
                .orElseThrow(()->new RuntimeException("No movie found"));

        Theatre theatre = theatreRepo.findById(showDTO.getTheatreId())
                .orElseThrow(()->new RuntimeException("No Theatre found"));

        show.setShowtime(showDTO.getShowtime());
        show.setPrice(showDTO.getPrice());
        show.setMovie(movie);
        show.setTheatre(theatre);

        return showRepo.save(show);
    }

    public List<Show> getAllShows() {
        return showRepo.findAll();
    }
}

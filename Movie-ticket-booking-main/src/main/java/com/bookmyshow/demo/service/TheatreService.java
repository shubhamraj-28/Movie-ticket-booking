package com.bookmyshow.demo.service;

import com.bookmyshow.demo.dtos.TheatreDTO;
import com.bookmyshow.demo.entities.Theatre;
import com.bookmyshow.demo.repo.TheatreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheatreService {

    @Autowired
    private TheatreRepo theatreRepo;

    public Theatre addTheatre(TheatreDTO theatreDTO) {
        Theatre theatre = new Theatre();

        theatre.setTheatreName(theatreDTO.getTheatreName());
        theatre.setTheatreLocation(theatreDTO.getTheatreLocation());
        theatre.setTheatreCapacity(theatreDTO.getTheatreCapacity());
        theatre.setTheatreScreenType(theatreDTO.getTheatreScreenType());

        return theatreRepo.save(theatre);
    }

    public List<Theatre> getTheatreByLocation(String location) {
        Optional<List<Theatre>> listOfTheatreBox = theatreRepo.findByLocation(location);

        if(listOfTheatreBox.isEmpty()) throw new RuntimeException("Theatres Not found");
        return listOfTheatreBox.get();
    }

    public Theatre updateTheatre(Long id, TheatreDTO theatreDTO) {
        Theatre theatre = theatreRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Theatre with"+id+"Not found"));

        theatre.setTheatreName(theatreDTO.getTheatreName());
        theatre.setTheatreLocation(theatreDTO.getTheatreLocation());
        theatre.setTheatreCapacity(theatreDTO.getTheatreCapacity());
        theatre.setTheatreScreenType(theatreDTO.getTheatreScreenType());

        return theatreRepo.save(theatre);
    }

    public void deleteMovie(Long id) {
        Theatre theatre = theatreRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        theatreRepo.delete(theatre);
    }
}

package com.bookmyshow.demo.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShowDTO {
    private LocalDateTime showtime;
    private Double price;
    private Long movieId;
    private Long theatreId;

    public LocalDateTime getShowtime() {
        return showtime;
    }

    public Double getPrice() {
        return price;
    }

    public Long getMovieId() {
        return movieId;
    }

    public Long getTheatreId() {
        return theatreId;
    }
}

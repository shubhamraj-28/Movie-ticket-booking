package com.bookmyshow.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name="shows")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime showtime;
    private Double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id",nullable = false)
    private Movie movie;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "theatre_id",nullable = false)
    private Theatre theatre ;

    @OneToMany(mappedBy ="show",fetch = FetchType.LAZY)
    private List<Booking> bookings;

    public void setShowtime(LocalDateTime showtime) {
        this.showtime = showtime;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public Long getId() {
        return null;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDateTime getShowTime() {
        return showtime;
    }

    public Theatre getTheatre() {
        return theatre;
    }
}

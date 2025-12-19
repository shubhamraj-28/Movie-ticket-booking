package com.bookmyshow.demo.entities;

import com.bookmyshow.demo.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numOfSeats;
    private Double price;
    private LocalDateTime bookingTime;
    private BookingStatus bookingStatus;


    public void setBookingStatus(BookingStatus status) {
        this.bookingStatus = status;
    }

    public void setNumOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setSeats(List<String> seats) {
        this.seats = seats;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public int getNumOfSeats() {
        return numOfSeats;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public List<String> getSeats() {
        return seats;
    }

    public Show getShow() {
        return show;
    }

    public User getUser() {
        return user;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "booking_seat_numbers")
    private List<String> seats;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "show_id",nullable = false)
    private Show show;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
}

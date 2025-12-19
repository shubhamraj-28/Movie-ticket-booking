package com.bookmyshow.demo.dtos;

import com.bookmyshow.demo.enums.BookingStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BookingDTO {

    private int numOfSeats;
    private Double price;
    private LocalDateTime bookingTime;
    private BookingStatus status;
    private List<String> seats;

    private Long userId;
    private Long showId;

    public Long getShowId() {
        return showId;
    }

    public Integer getNumOfSeats() {
        return numOfSeats;
    }

    public List<String> getSeats() {
        return null;
    }

    public Long getUserId() {
        return userId;
    }
}

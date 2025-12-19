package com.bookmyshow.demo.controller;

import com.bookmyshow.demo.dtos.BookingDTO;
import com.bookmyshow.demo.entities.Booking;
import com.bookmyshow.demo.enums.BookingStatus;
import com.bookmyshow.demo.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/createBooking")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingDTO bookingDTO){
        return ResponseEntity.ok(bookingService.createBooking(bookingDTO));
    }

    @GetMapping("/getUserBooking/{userId}")
    public ResponseEntity<List<Booking>> getUserBooking(@PathVariable Long userId){
        return ResponseEntity.ok(bookingService.getUserBooking(userId));
    }

    @GetMapping("/getShowBooking/{userId}")
    public ResponseEntity<List<Booking>> getShowBooking(@PathVariable Long showId){
        return ResponseEntity.ok(bookingService.getShowBooking(showId));
    }

    @PutMapping("/{id}/confirm")
    public ResponseEntity<Booking> confirmBooking(@PathVariable Long id){
        return ResponseEntity.ok(bookingService.confirmBooking(id));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Booking> cancelBooking(@PathVariable Long id){
        return ResponseEntity.ok(bookingService.cancelBooking(id));
    }

    @GetMapping("/getBookingsByStatus/{status}")
    public ResponseEntity<List<Booking>> getBookingsByStatus(@PathVariable BookingStatus status){
        return ResponseEntity.ok(bookingService.getBookingsByStatus(status));
    }
}

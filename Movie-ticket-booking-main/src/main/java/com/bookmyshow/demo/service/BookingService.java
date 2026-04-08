package com.bookmyshow.demo.service;

import com.bookmyshow.demo.dtos.BookingDTO;
import com.bookmyshow.demo.entities.Booking;
import com.bookmyshow.demo.entities.Show;
import com.bookmyshow.demo.entities.User;
import com.bookmyshow.demo.enums.BookingStatus;
import com.bookmyshow.demo.repo.BookingRepo;
import com.bookmyshow.demo.repo.ShowRepo;
import com.bookmyshow.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private ShowRepo showRepo;

    @Autowired
    private UserRepo userRepo;

    @Transactional
    public Booking createBooking(BookingDTO bookingDTO) {
        Show show = showRepo.findByIdWithPessimisticLock(bookingDTO.getShowId())
                .orElseThrow(()->new RuntimeException("No show found"));

        if(!isSeatAvailable(show,bookingDTO.getNumOfSeats())){
            throw new RuntimeException("Not enough seats available");
        }

        if(bookingDTO.getSeats().size() != bookingDTO.getNumOfSeats()){
            throw new RuntimeException("Number of seats should match the selected number of seats");
        }

        validateDuplicates(show,bookingDTO.getSeats());

        User user = userRepo.findById(bookingDTO.getUserId())
                .orElseThrow(()->new RuntimeException("No user found"));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShow(show);
        booking.setNumOfSeats(bookingDTO.getNumOfSeats());
        booking.setSeats(bookingDTO.getSeats());
        booking.setBookingTime(LocalDateTime.now());
        booking.setPrice(calculateAmount(show.getPrice(),bookingDTO.getNumOfSeats()));
        booking.setBookingStatus(BookingStatus.PENDING);

        return bookingRepo.save(booking);
    }

    public boolean isSeatAvailable(Show show,Integer numOfSeats){
        int bookedSeats = show.getBookings().stream()
                .filter(booking-> booking.getBookingStatus() != BookingStatus.CANCELLED)
                .mapToInt(Booking::getNumOfSeats)
                .sum();

        return (show.getTheatre().getTheatreCapacity() - bookedSeats) >= numOfSeats;
    }

    public void validateDuplicates(Show show,List<String> seats){
        Set<String> occupiedSeats = show.getBookings().stream()
                .filter(b->b.getBookingStatus() != BookingStatus.CANCELLED)
                .flatMap(b->b.getSeats().stream())
                .collect(Collectors.toSet());

        List<String> duplicateSeats = seats.stream()
                .filter(occupiedSeats::contains)
                .toList();

        if(!duplicateSeats.isEmpty()){
            throw new RuntimeException("Seats are already booked");
        }
    }

    public Double calculateAmount(Double price,Integer numOfSeats){
        return price * numOfSeats;
    }

    public List<Booking> getUserBooking(Long userId) {
        return bookingRepo.findByUserId(userId);
    }

    public List<Booking> getShowBooking(Long showId) {
        return bookingRepo.findByShowId(showId);
    }

    public Booking confirmBooking(Long id) {
        Booking booking = bookingRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Booking not found"));

        if(booking.getBookingStatus() != BookingStatus.PENDING){
            throw new RuntimeException("Booking is not in pending state");
        }

        booking.setBookingStatus(BookingStatus.CONFIRMED);

        return bookingRepo.save(booking);
    }

    public Booking cancelBooking(Long id) {
        Booking booking = bookingRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Booking not found"));

        validateCancellation(booking);

        booking.setBookingStatus(BookingStatus.CANCELLED);

        return bookingRepo.save(booking);
    }

    public void validateCancellation(Booking booking){
        LocalDateTime showTime = booking.getShow().getShowTime();
        LocalDateTime deadlineTime = showTime.minusHours(2);

        if(LocalDateTime.now().isAfter(deadlineTime)){
            throw new RuntimeException("Can't cancel booking now");
        }

        if(booking.getBookingStatus() == BookingStatus.CANCELLED){
            throw new RuntimeException("Booking already cancelled");
        }
    }

    public List<Booking> getBookingsByStatus(BookingStatus status) {
        return bookingRepo.findByBookingStatus(status);
    }
}

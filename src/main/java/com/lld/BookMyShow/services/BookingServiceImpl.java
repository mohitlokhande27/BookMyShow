package com.lld.BookMyShow.services;

import com.lld.BookMyShow.exceptions.BookingException;
import com.lld.BookMyShow.exceptions.BookingSeatBlockedException;
import com.lld.BookMyShow.models.*;
import com.lld.BookMyShow.repositories.BookingRepository;
import com.lld.BookMyShow.repositories.ShowRepository;
import com.lld.BookMyShow.repositories.ShowSeatRepository;
import com.lld.BookMyShow.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
public class BookingServiceImpl implements BookingService{

    private final BookingRepository bookingRepository;
    private final ShowRepository showRepository;
    private final ShowSeatRepository showSeatRepository;
    private final UserRepository userRepository;
    private final PriceCalculatorService priceCalculatorService;

    public BookingServiceImpl(BookingRepository bookingRepository, ShowRepository showRepository,
                              ShowSeatRepository showSeatRepository, UserRepository userRepository,
                              PriceCalculatorService priceCalculatorService) {
        this.bookingRepository = bookingRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.userRepository = userRepository;
        this.priceCalculatorService = priceCalculatorService;
    }

    @Override
    public Booking bookMovie(List<Long> showSeatIds, Long userId, Long showId) {
        // 1. sanity check, get show, user details from DB
        Optional<Show> showOptional = showRepository.findById(showId);
        if(showOptional.isEmpty()) {
//            throw new IllegalArgumentException("Show not found");
            throw new BookingException("Show not found");
        }
        Show bookedShow = showOptional.get();

        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) {
//            throw new IllegalArgumentException("User not found");
            throw new BookingException("User not found");
        }
        User bookedByUser = userOptional.get();

        // ideally lock is required from step 2 to 4.2.1, but springboot code is complex, so keeping all lines under lock
        // ------- Acquire the lock ---------------
        // 2. get all the ShowSeats corresponding to showSeatIds from DB
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);

        // 3. check if seats are available
        for(ShowSeat showSeat: showSeats) {
            // 4.1. if not, throw exception
            if (!showSeat.getStatus().equals(ShowSeatStatus.AVAILABLE)) {
                if(showSeat.getStatus().equals(ShowSeatStatus.BOOKED) &&
                        Duration.between(showSeat.getBookedAt().toInstant(), new Date().toInstant()).toMinutes() > 15) {
                    // capture this in catch block and release the lock
//                    throw new RuntimeException("Blocked seats must be released");
                    throw new BookingSeatBlockedException("Blocked seats must be released");
                }
//                throw new RuntimeException("Show seat not available");
                throw new BookingException("Show seat not available");
            }
        }
            // 4.2.1 if available, change status to BLOCKED
        for(ShowSeat showSeat: showSeats) {
            showSeat.setStatus(ShowSeatStatus.BLOCKED);
            showSeat.setBookedAt(new Date());

            // 4.2.2 save status to DB
            showSeatRepository.save(showSeat);
        }
        // ------- Release the lock ---------------
        // 4.2.3 save booking to DB and return booking object created
        Booking booking = new Booking();
        booking.setBookedAt(new Date());
        booking.setBookedBy(bookedByUser);
        booking.setPayments(new ArrayList<>());
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setShowSeats(showSeats);
        booking.setTotalAmount(priceCalculatorService.calculatePrice(showSeats, bookedShow)); // breaks SRP so do it separately

        return bookingRepository.save(booking);
    }
}

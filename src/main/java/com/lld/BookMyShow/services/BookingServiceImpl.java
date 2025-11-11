package com.lld.BookMyShow.services;

import com.lld.BookMyShow.models.Booking;
import com.lld.BookMyShow.repositories.BookingRepository;
import com.lld.BookMyShow.repositories.ShowRepository;
import com.lld.BookMyShow.repositories.ShowSeatRepository;
import com.lld.BookMyShow.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
public class BookingServiceImpl implements BookingService{
    // ideally lock is required from step 2 to 4.2.1, but springboot code is complex, so keeping all lines under lock

    private final BookingRepository bookingRepository;
    private final ShowRepository showRepository;
    private final ShowSeatRepository showSeatRepository;
    private final UserRepository userRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, ShowRepository showRepository,
                              ShowSeatRepository showSeatRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Booking bookMovie(List<Long> showSeatIds, Long userId, Long showId) {
        // 1. sanity check, get show, user, details from DB
        // ------- Acquire the lock ---------------
        // 2. get all the ShowSeats corresponding to showSeatIds from DB
        // 3. check if seats are available
            // 4.1. if not, throw exception
            // 4.2.1 if available, change status to BLOCKED
        // ------- Release the lock ---------------
                // 4.2.2 save status to DB
                // 4.2.3 save booking to DB and return booking object created

        return null;
    }
}

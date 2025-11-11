package com.lld.BookMyShow.services;

import com.lld.BookMyShow.models.Booking;

import java.util.List;

public interface BookingService {
    Booking bookMovie(List<Long> showSeatIds, Long userId, Long showId);
}

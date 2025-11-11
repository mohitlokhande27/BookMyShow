package com.lld.BookMyShow.controllers;

import com.lld.BookMyShow.dtos.BookingRequestDto;
import com.lld.BookMyShow.dtos.BookingResponseDto;
import com.lld.BookMyShow.dtos.ResponseStatus;
import com.lld.BookMyShow.models.Booking;
import com.lld.BookMyShow.services.BookingService;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public BookingResponseDto bookMovie(BookingRequestDto bookingRequestDto) {
        BookingResponseDto bookingResponseDto = new BookingResponseDto();
        try {
            Booking booking = bookingService.bookMovie(bookingRequestDto.getShowSeatIds(),
                    bookingRequestDto.getUserId(), bookingRequestDto.getShowId());
            bookingResponseDto.setBookingId(booking.getId());
            bookingResponseDto.setStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            bookingResponseDto.setStatus(ResponseStatus.FAILURE);
            bookingResponseDto.setFailureMessage("Booking failure " + e.getMessage());
        }
        return bookingResponseDto;
    }
}

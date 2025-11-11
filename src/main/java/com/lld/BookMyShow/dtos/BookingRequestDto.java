package com.lld.BookMyShow.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookingRequestDto {
    private List<Long> showSeatIds;     // combo of showId, seatId
    private Long userId;
    private Long showId;        // as a good measure even though we can get it from showSeatId
}

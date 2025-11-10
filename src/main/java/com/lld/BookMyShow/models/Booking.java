package com.lld.BookMyShow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel{
    @ManyToOne
    private User bookedBy;
    private Date bookedAt;
    private int totalAmount;
    @OneToMany
    private List<Payment> payments;
    @ManyToMany // ideally it should be OneToMany but as user can cancel 1-2 showseat out of total 5 showSeat booked
    // cancellation will be counted as another booking, so ManyToMany
    private List<ShowSeat> showSeats;
    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;
}

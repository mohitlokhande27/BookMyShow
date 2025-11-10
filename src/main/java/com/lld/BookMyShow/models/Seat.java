package com.lld.BookMyShow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel{
    private String number;
    @ManyToOne
    private SeatType seatType;
    private int rowVal; // row and col are Mysql reserved keywords so use rowVal, colVal
    private int colVal;
}

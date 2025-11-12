package com.lld.BookMyShow.services;

import com.lld.BookMyShow.models.Show;
import com.lld.BookMyShow.models.ShowSeat;

import java.util.List;

public interface PriceCalculatorService {
    double calculatePrice(List<ShowSeat> showSeats, Show show);
}

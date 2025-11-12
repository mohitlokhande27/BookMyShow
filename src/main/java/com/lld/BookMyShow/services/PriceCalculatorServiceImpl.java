package com.lld.BookMyShow.services;

import com.lld.BookMyShow.models.Show;
import com.lld.BookMyShow.models.ShowSeat;
import com.lld.BookMyShow.models.ShowSeatType;
import com.lld.BookMyShow.repositories.ShowSeatTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculatorServiceImpl implements PriceCalculatorService{

    private final ShowSeatTypeRepository showSeatRepository;

    public PriceCalculatorServiceImpl(ShowSeatTypeRepository showSeatRepository) {
        this.showSeatRepository = showSeatRepository;
    }

    @Override
    public double calculatePrice(List<ShowSeat> showSeats, Show show) {
        double price = 0;
        List<ShowSeatType> showSeatTypes = showSeatRepository.findByShow(show);
        for(ShowSeat showSeat: showSeats) {
            for(ShowSeatType showSeatType: showSeatTypes) {
                if(showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())) {
                    price += showSeatType.getPrice();
                    break;
                }
            }
        }
        return price;
    }
}

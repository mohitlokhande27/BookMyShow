package com.lld.BookMyShow.repositories;

import com.lld.BookMyShow.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
    @Override
    List<ShowSeat> findAllById(Iterable<Long> longs);

    /*
    * If id is present => update
    * If id is not present => insert
    * */
    @Override
    ShowSeat save(ShowSeat showSeat);
}

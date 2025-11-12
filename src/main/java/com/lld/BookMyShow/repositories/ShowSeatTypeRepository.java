package com.lld.BookMyShow.repositories;

import com.lld.BookMyShow.models.Show;
import com.lld.BookMyShow.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long> {
    List<ShowSeatType> findByShow(Show show);
}

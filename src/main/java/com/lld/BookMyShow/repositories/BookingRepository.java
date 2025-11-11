package com.lld.BookMyShow.repositories;

import com.lld.BookMyShow.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}

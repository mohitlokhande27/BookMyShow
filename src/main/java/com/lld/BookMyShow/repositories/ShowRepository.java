package com.lld.BookMyShow.repositories;

import com.lld.BookMyShow.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<model class or DB table name, PK of table> helps to make DB call
// no need of any annotation as JpaRepository is @NoRepositoryBean
public interface ShowRepository extends JpaRepository<Show, Long> {
}

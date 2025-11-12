package com.lld.BookMyShow.repositories;

import com.lld.BookMyShow.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository<model class or DB table name, PK of table> helps to make DB call
// no need of any annotation as JpaRepository is @NoRepositoryBean
// Repository -> JpaRepository (interface) -> Hibernate(ORM and this implements interface, provides methods,
// query details) -> JDBC -> DB
public interface ShowRepository extends JpaRepository<Show, Long> {
    @Override
    Optional<Show> findById(Long id);
}

package com.lld.BookMyShow.repositories;

import com.lld.BookMyShow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

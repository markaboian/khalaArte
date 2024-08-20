package com.khala_arte.ms_users.repository;

import com.khala_arte.ms_users.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = ?1 ORDER BY u.lastName")
    Optional<User> findUserByEmail(String email);

}

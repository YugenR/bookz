package com.vinai.bookz.repositories;

import com.vinai.bookz.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(
            "SELECT u FROM User AS u WHERE " +
                    "(u.name LIKE %:keyword%) OR " +
                    "(u.surname LIKE %:keyword%) OR " +
                    "(u.email LIKE %:keyword%)"
    )
    Page<User> findAll(
            Pageable pageable,
            @Param("keyword") String keyword);
    Optional<User> findByEmail(String email);


}

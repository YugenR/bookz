package com.vinai.bookz.repositories;

import com.vinai.bookz.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, String> {

    @Query(
            "SELECT b FROM Book AS b WHERE " +
                    "(b.title LIKE %:keyword%) OR " +
                    "(b.author LIKE %:keyword%) OR " +
                    "(b.isbn LIKE %:keyword%)"
    )
    Page<Book> findAll(
            Pageable pageable,
            @Param("keyword") String keyword);
}

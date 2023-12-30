package com.vinai.bookz.repositories;

import com.vinai.bookz.entities.Book;
import com.vinai.bookz.entities.UserBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserBookRepository extends JpaRepository<UserBook, UserBook.UserBookId> {
    @Query(
            "SELECT ub FROM UserBook AS ub WHERE " +
                    "ub.user.id = :userId AND " +
                    "(ub.book.author LIKE %:keyword% OR " +
                    "ub.book.title LIKE %:keyword% OR " +
                    "ub.book.isbn LIKE %:keyword%)"
    )
    Page<UserBook> findAllByUser(
            Pageable pageable,
            @Param("userId") Long userId,
            @Param("keyword") String keyword
    );

}

package com.vinai.bookz.repositories;

import com.vinai.bookz.entities.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBookRepository extends JpaRepository<UserBook, UserBook.UserBookId> {


}

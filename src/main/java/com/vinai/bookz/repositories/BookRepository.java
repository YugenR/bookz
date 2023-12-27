package com.vinai.bookz.repositories;

import com.vinai.bookz.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {



}

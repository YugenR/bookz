package com.vinai.bookz.entities;


import com.vinai.bookz.dtos.BookDTO;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Entity
@SQLRestriction("deleted <> true")
@SQLDelete(sql = "UPDATE book SET deleted = true, deleted_at = current_timestamp WHERE id = ?")
@RequiredArgsConstructor
public class Book {

    public Book(String title, String author, String isbn, String plot) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.plot = plot;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String author;
    @Column(unique = true)
    private String isbn;
    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime deletedAt;

    private boolean deleted;

    // todo: textarea
    private String plot;

    public BookDTO.BookData toDTOData() {
        return new BookDTO.BookData(
                id, title, author, isbn, createdAt
        );
    }

    public BookDTO.BookDetail toDTODetail() {
        return new BookDTO.BookDetail(
                id, title, author, isbn, createdAt, plot
        );
    }

    public void update(String title, String author, String isbn, String plot) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.plot = plot;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

}

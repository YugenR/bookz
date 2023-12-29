package com.vinai.bookz.entities;


import com.vinai.bookz.dtos.BookDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@SQLRestriction("deleted <> true")
@SQLDelete(sql = "UPDATE book SET isbn = CONCAT(isbn, '_deleted_', current_timestamp), deleted = true, deleted_at = current_timestamp WHERE isbn = ?")
@RequiredArgsConstructor
public class Book {

    @Column
    private String title;
    @Column
    private String author;

    @Id
    @Getter
    private String isbn;
    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column
    private LocalDateTime deletedAt;
    private boolean deleted;
    @Column(columnDefinition = "TEXT")
    private String plot;

    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<UserBook> userBooks = new HashSet<>();

    public Book(String title, String author, String isbn, String plot) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.plot = plot;
    }

    public BookDTO.BookData toDTOData() {
        return new BookDTO.BookData(
                title, author, isbn, plot, createdAt
        );
    }

    public BookDTO.BookDetail toDTODetail() {
        return new BookDTO.BookDetail(
                title, author, isbn, createdAt, plot,
                userBooks.stream()
                        .mapToInt(UserBook::getTimes)
                        .sum()

        );
    }

    public void update(String title, String author, String plot) {
        this.title = title;
        this.author = author;
        this.plot = plot;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

}

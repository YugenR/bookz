package com.vinai.bookz.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.io.Serializable;

@Entity
@NoArgsConstructor
public class UserBook implements Serializable {

    public UserBook(User user, Book book, Integer times) {
        this.id = new UserBookId(user, book);
        this.user = user;
        this.book = book;
        this.times = times;

    }


    @Embeddable
    @NoArgsConstructor
    public static class UserBookId implements Serializable {

        public UserBookId(User user, Book book) {
            this.userId = user.getId();
            this.bookId = book.getIsbn();
        }
        private long userId;
        private String bookId;
    }

    @EmbeddedId
    @NonNull
    private UserBookId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn
    private User user;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn
    @Getter
    private Book book;

    @Getter
    @Setter
    @Column
    private Integer times;

}

package com.vinai.bookz.entities;


import jakarta.persistence.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Entity
public class UserBook implements Serializable {

    public UserBook(Long userId, Long bookId) {
        this.id = new UserBookId(userId, bookId);
        this.times = 1;
    }


    @Embeddable
    public static class UserBookId implements Serializable {

        public UserBookId(Long userId, Long bookId) {
            this.userId = userId;
            this.bookId = bookId;
        }
        private long userId, bookId;

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
    private Book book;

    @Column
    private Integer times;

    public Integer getTimes() {
        return times;
    }

}

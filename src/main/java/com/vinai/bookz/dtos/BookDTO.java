package com.vinai.bookz.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

public class BookDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BookCreate {
        @NonNull
        private String title;
        @NonNull
        private String author;
        @NonNull
        private String isbn;
        @NonNull
        private String plot;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BookUpdate {
        @NonNull
        private String title;
        @NonNull
        private String author;
        @NonNull
        private String plot;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BookData {
        private Long id;
        private String title;
        private String author;
        private String isbn;
        private LocalDateTime createdAt;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BookDetail {
        private Long id;
        private String title;
        private String author;
        private String isbn;
        private LocalDateTime createdAt;
        private String plot;
    }

}

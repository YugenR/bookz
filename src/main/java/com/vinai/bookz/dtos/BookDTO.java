package com.vinai.bookz.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class BookDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BookCreate {
        @NotEmpty
        private String title;
        @NotEmpty
        private String author;
        @NotEmpty
        private String isbn;
        private String plot;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BookUpdate {
        @NotEmpty
        private String title;
        @NotEmpty
        private String author;
        @NotNull
        private String plot;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BookData {
        private String title;
        private String author;
        private String isbn;
        private LocalDateTime createdAt;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BookDetail {
        private String title;
        private String author;
        private String isbn;
        private LocalDateTime createdAt;
        private String plot;
        private Integer timesRead;
    }



}
